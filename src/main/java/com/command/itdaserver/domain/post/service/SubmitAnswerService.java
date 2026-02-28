package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.Answer;
import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.Question;
import com.command.itdaserver.domain.post.domain.QuestionOption;
import com.command.itdaserver.domain.post.domain.enums.AnswerType;
import com.command.itdaserver.domain.post.domain.repository.AnswerRepository;
import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.domain.repository.QuestionOptionRepository;
import com.command.itdaserver.domain.post.domain.repository.QuestionRepository;
import com.command.itdaserver.domain.post.exceptions.DuplicateAnswerException;
import com.command.itdaserver.domain.post.exceptions.InvalidQuestionOptionException;
import com.command.itdaserver.domain.post.exceptions.MultipleSelectionNotAllowedException;
import com.command.itdaserver.domain.post.exceptions.PostClosedException;
import com.command.itdaserver.domain.post.exceptions.RequiredAnswerMissingException;
import com.command.itdaserver.domain.post.exceptions.PostNotFoundException;
import com.command.itdaserver.domain.post.exceptions.QuestionNotFoundException;
import com.command.itdaserver.domain.post.presentation.dto.request.SubmitAnswerRequest;
import com.command.itdaserver.domain.post.presentation.dto.response.AnswerResponse;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.user.exception.UserNotFoundException;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubmitAnswerService {

    private final PostRepository postRepository;
    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository questionOptionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<AnswerResponse> execute(Long postId, SubmitAnswerRequest request, CustomUserDetails userDetails) {

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        // 마감된 게시글 지원 방지
        if (post.isClosed()) {
            throw PostClosedException.EXCEPTION;
        }

        User answerer = userRepository.findByUserId(userDetails.getUserId())
                .orElseThrow(UserNotFoundException::new);

        List<AnswerResponse> responses = new ArrayList<>();

        for (SubmitAnswerRequest.AnswerDto dto : request.getAnswers()) {

            // questionId가 해당 post 소속인지 검증
            Question question = questionRepository.findByIdAndPost(dto.getQuestionId(), post)
                    .orElseThrow(QuestionNotFoundException::new);

            // 중복 제출 방지
            if (answerRepository.existsByAnswererAndQuestion(answerer, question)) {
                throw DuplicateAnswerException.EXCEPTION;
            }

            if (question.getAnswerType() == AnswerType.OBJECTIVE) {

                boolean noOptionSelected = dto.getSelectedOptionIds() == null || dto.getSelectedOptionIds().isEmpty();
                // 객관식인데 옵션을 하나도 선택하지 않은 경우
                if (noOptionSelected) {
                    if (question.isRequired()) throw RequiredAnswerMissingException.EXCEPTION;
                    continue;
                }

                // 객관식인데 multiple이 false인데 여러 옵션 선택한 경우 예외
                if (!question.isMultiple() && dto.getSelectedOptionIds().size() > 1) {
                    throw MultipleSelectionNotAllowedException.EXCEPTION;
                }

                List<AnswerResponse.SelectedOptionDto> selectedOptionDtos = new ArrayList<>();

                for (Long optionId : dto.getSelectedOptionIds()) {
                    // option이 해당 question 소속인지 검증
                    QuestionOption option = questionOptionRepository.findByIdAndQuestion(optionId, question)
                            .orElseThrow(InvalidQuestionOptionException::new);

                    answerRepository.save(Answer.builder()
                            .answerer(answerer)
                            .question(question)
                            .selectedOption(option)
                            .build());

                    selectedOptionDtos.add(new AnswerResponse.SelectedOptionDto(
                            option.getAnswerNumber(),
                            option.getAnswerContent()
                    ));
                }

                responses.add(new AnswerResponse(question, selectedOptionDtos));

            } else { // SUBJECTIVE

                boolean noTextAnswer = dto.getTextAnswer() == null || dto.getTextAnswer().isBlank();
                if (noTextAnswer) {
                    if (question.isRequired()) throw RequiredAnswerMissingException.EXCEPTION;
                    continue;
                }

                answerRepository.save(Answer.builder()
                        .answerer(answerer)
                        .question(question)
                        .textAnswer(dto.getTextAnswer())
                        .build());

                responses.add(new AnswerResponse(question, dto.getTextAnswer()));
            }
        }

        return responses;
    }
}