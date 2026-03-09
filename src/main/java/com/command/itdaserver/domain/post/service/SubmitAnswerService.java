package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.Answer;
import com.command.itdaserver.domain.post.domain.Application;
import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.Question;
import com.command.itdaserver.domain.post.domain.QuestionOption;
import com.command.itdaserver.domain.post.domain.enums.AnswerType;
import com.command.itdaserver.domain.post.domain.repository.AnswerRepository;
import com.command.itdaserver.domain.post.domain.repository.ApplicationRepository;
import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.domain.repository.QuestionOptionRepository;
import com.command.itdaserver.domain.post.domain.repository.QuestionRepository;
import com.command.itdaserver.domain.post.exceptions.DuplicateApplicationException;
import com.command.itdaserver.domain.post.exceptions.DuplicateOptionSelectException;
import com.command.itdaserver.domain.post.exceptions.InvalidQuestionOptionException;
import com.command.itdaserver.domain.post.exceptions.MultipleSelectionNotAllowedException;
import com.command.itdaserver.domain.post.exceptions.PostClosedException;
import com.command.itdaserver.domain.post.exceptions.PostNotFoundException;
import com.command.itdaserver.domain.post.exceptions.QuestionNotFoundException;
import com.command.itdaserver.domain.post.exceptions.RequiredAnswerMissingException;
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
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SubmitAnswerService {

    private final PostRepository postRepository;
    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository questionOptionRepository;
    private final AnswerRepository answerRepository;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<AnswerResponse> execute(Long postId, SubmitAnswerRequest request, CustomUserDetails userDetails) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        // 마감된 게시글 지원 방지
        if (post.isClosed()) {
            throw PostClosedException.EXCEPTION;
        }

        User applicant = userRepository.findByUserId(userDetails.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        // 중복 지원 방지
        if (applicationRepository.existsByPostAndApplicant(post, applicant)) {
            throw DuplicateApplicationException.EXCEPTION;
        }

        // 필수 질문이 answers 리스트에 포함됐는지 사전 검증
        Set<Long> submittedQuestionIds = request.getAnswers().stream()
                .map(SubmitAnswerRequest.AnswerDto::getQuestionId)
                .collect(Collectors.toSet());

        boolean hasRequiredMissing = post.getQuestions().stream()
                .filter(Question::isRequired)
                .anyMatch(q -> !submittedQuestionIds.contains(q.getId()));

        if (hasRequiredMissing) {
            throw RequiredAnswerMissingException.EXCEPTION;
        }

        // Application 생성
        Application application = applicationRepository.save(Application.create(post, applicant));

        List<AnswerResponse> responses = new ArrayList<>();

        for (SubmitAnswerRequest.AnswerDto dto : request.getAnswers()) {

            // questionId가 해당 post 소속인지 검증
            Question question = questionRepository.findByIdAndPost(dto.getQuestionId(), post)
                    .orElseThrow(() -> QuestionNotFoundException.EXCEPTION);

            if (question.getAnswerType() == AnswerType.OBJECTIVE) {

                boolean noOptionSelected = dto.getSelectedOptionIds() == null || dto.getSelectedOptionIds().isEmpty();
                // 객관식인데 옵션을 하나도 선택하지 않은 경우
                if (noOptionSelected) {
                    if (question.isRequired()) throw RequiredAnswerMissingException.EXCEPTION;
                    continue;
                }

                // 중복 optionId 검증
                List<Long> selectedIds = dto.getSelectedOptionIds();
                if (selectedIds.stream().distinct().count() != selectedIds.size()) {
                    throw DuplicateOptionSelectException.EXCEPTION;
                }

                // 객관식인데 multiple이 false인데 여러 옵션 선택한 경우 예외
                if (!question.isMultiple() && selectedIds.size() > 1) {
                    throw MultipleSelectionNotAllowedException.EXCEPTION;
                }

                List<AnswerResponse.SelectedOptionDto> selectedOptionDtos = new ArrayList<>();

                for (Long optionId : selectedIds) {
                    // option이 해당 question 소속인지 검증
                    QuestionOption option = questionOptionRepository.findByIdAndQuestion(optionId, question)
                            .orElseThrow(() -> InvalidQuestionOptionException.EXCEPTION);

                    answerRepository.save(Answer.builder()
                            .application(application)
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
                        .application(application)
                        .question(question)
                        .textAnswer(dto.getTextAnswer())
                        .build());

                responses.add(new AnswerResponse(question, dto.getTextAnswer()));
            }
        }

        return responses;
    }
}
