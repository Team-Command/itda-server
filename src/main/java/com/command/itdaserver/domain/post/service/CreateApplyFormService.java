package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.Question;
import com.command.itdaserver.domain.post.domain.QuestionOption;
import com.command.itdaserver.domain.post.domain.enums.AnswerType;
import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.exceptions.ApplyFormAlreadyExistsException;
import com.command.itdaserver.domain.post.exceptions.MissingQuestionOptionException;
import com.command.itdaserver.domain.post.exceptions.PostNotFoundException;
import com.command.itdaserver.domain.post.exceptions.SubjectiveQuestionHasOptionsException;
import com.command.itdaserver.domain.post.exceptions.UnauthorizedPostAccessException;
import com.command.itdaserver.domain.post.presentation.dto.request.CreateFormRequest;
import com.command.itdaserver.domain.post.presentation.dto.response.QuestionResponse;
import com.command.itdaserver.global.auth.CustomUserDetails;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CreateApplyFormService {

    private final PostRepository postRepository;

    @Transactional
    public List<QuestionResponse> execute(Long postId, CreateFormRequest request, CustomUserDetails userDetails) {

        // 반환을 위해 저장할 Question 리스트
        List<QuestionResponse> questions = new ArrayList<>();

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        // 작성자 권한 확인
        if (!post.getWriter().getUserId().equals(userDetails.getUserId())) {
            throw UnauthorizedPostAccessException.EXCEPTION;
        }

        // 이미 지원 폼이 존재하는 경우
        if (!post.getQuestions().isEmpty()) {
            throw ApplyFormAlreadyExistsException.EXCEPTION;
        }

        for (var qDto : request.getQuestions()) {
            Question question = Question.builder()
                    .questionNumber(qDto.getQuestionNumber())
                    .questionContent(qDto.getQuestionContent())
                    .answerType(qDto.getAnswerType())
                    .multiple(qDto.getMultiple())
                    .required(qDto.getRequired())
                    .build();
            if (qDto.getAnswerType() == AnswerType.OBJECTIVE) { // 객관식인 경우 옵션 추가
                if (qDto.getOptions() == null  || qDto.getOptions().isEmpty()) {
                    throw MissingQuestionOptionException.EXCEPTION;
                }
                for (var optDto : qDto.getOptions()) {
                    QuestionOption option = QuestionOption.builder()
                            .answerNumber(optDto.getAnswerNumber())
                            .answerContent(optDto.getAnswerContent())
                            .build();
                    question.addOptions(option); // Cascade 이기 때문에 DB 저장됨
                }
            } else { // 주관식인 경우 옵션이 있으면 예외
                if (qDto.getOptions() != null && !qDto.getOptions().isEmpty()) {
                    throw SubjectiveQuestionHasOptionsException.EXCEPTION;
                }
            }
            post.addQuestion(question); // Cascade 이기 때문에 DB 저장됨
            questions.add(new QuestionResponse(question));
        }
        return questions;
    }
}
