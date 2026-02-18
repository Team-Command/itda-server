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
import com.command.itdaserver.domain.post.exceptions.MissingSelectedOptionException;
import com.command.itdaserver.domain.post.exceptions.PostNotFoundException;
import com.command.itdaserver.domain.post.exceptions.QuestionNotFoundException;
import com.command.itdaserver.domain.post.presentation.dto.request.SubmitAnswerRequest;
import com.command.itdaserver.domain.post.presentation.dto.response.AnswerResponse;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.global.auth.CustomUserDetails;
import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;
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

        User answerer = userRepository.findByUserId(userDetails.getUserId())
                .orElseThrow(() -> new ItdaException(ErrorCode.USER_NOT_FOUND));

        List<AnswerResponse> responses = new ArrayList<>();

        for (SubmitAnswerRequest.AnswerDto dto : request.getAnswers()) {

            // 2-2: questionId가 해당 post 소속인지 검증
            Question question = questionRepository.findByIdAndPost(dto.getQuestionId(), post)
                    .orElseThrow(QuestionNotFoundException::new);

            // 2-3: 중복 제출 방지
            if (answerRepository.existsByAnswererAndQuestion(answerer, question)) {
                throw DuplicateAnswerException.EXCEPTION;
            }

            if (question.getAnswerType() == AnswerType.OBJECTIVE) {

                if (dto.getSelectedOptionIds() == null || dto.getSelectedOptionIds().isEmpty()) {
                    throw MissingSelectedOptionException.EXCEPTION;
                }

                List<AnswerResponse.SelectedOptionDto> selectedOptionDtos = new ArrayList<>();

                for (Long optionId : dto.getSelectedOptionIds()) {
                    // 2-1: option이 해당 question 소속인지 검증
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