package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.ApplyForm;
import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.Question;
import com.command.itdaserver.domain.post.domain.QuestionOption;
import com.command.itdaserver.domain.post.domain.enums.AnswerType;
import com.command.itdaserver.domain.post.domain.repository.ApplyFormRepository;
import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.exceptions.ApplyFormAlreadyExistsException;
import com.command.itdaserver.domain.post.exceptions.MissingQuestionOptionException;
import com.command.itdaserver.domain.post.exceptions.PostNotFoundException;
import com.command.itdaserver.domain.post.exceptions.SubjectiveQuestionHasOptionsException;
import com.command.itdaserver.domain.post.exceptions.UnauthorizedPostAccessException;
import com.command.itdaserver.domain.post.presentation.dto.request.CreateFormRequest;
import com.command.itdaserver.domain.post.presentation.dto.response.ApplyFormResponse;
import com.command.itdaserver.global.auth.CustomUserDetails;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateApplyFormService {

    private final PostRepository postRepository;
    private final ApplyFormRepository applyFormRepository;

    @Transactional
    public ApplyFormResponse execute(Long postId, CreateFormRequest request, CustomUserDetails userDetails) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        // 작성자 권한 확인
        if (!post.getWriter().getUserId().equals(userDetails.getUserId())) {
            throw UnauthorizedPostAccessException.EXCEPTION;
        }

        // 이미 지원 폼이 존재하는 경우
        if (applyFormRepository.existsByPost(post)) {
            throw ApplyFormAlreadyExistsException.EXCEPTION;
        }

        ApplyForm applyForm = ApplyForm.create(post, request.getTitle(), request.getDescription());

        for (var qDto : request.getQuestions()) {
            Question question = Question.builder()
                    .questionNumber(qDto.getQuestionNumber())
                    .questionContent(qDto.getQuestionContent())
                    .answerType(qDto.getAnswerType())
                    .multiple(qDto.getMultiple())
                    .required(qDto.getRequired())
                    .build();
            if (qDto.getAnswerType() == AnswerType.OBJECTIVE) {
                if (qDto.getOptions() == null || qDto.getOptions().isEmpty()) {
                    throw MissingQuestionOptionException.EXCEPTION;
                }
                for (var optDto : qDto.getOptions()) {
                    QuestionOption option = QuestionOption.builder()
                            .answerNumber(optDto.getAnswerNumber())
                            .answerContent(optDto.getAnswerContent())
                            .build();
                    question.addOptions(option);
                }
            } else {
                if (qDto.getOptions() != null && !qDto.getOptions().isEmpty()) {
                    throw SubjectiveQuestionHasOptionsException.EXCEPTION;
                }
            }
            applyForm.addQuestion(question);
        }

        applyFormRepository.save(applyForm);
        return new ApplyFormResponse(applyForm);
    }
}
