package com.command.itdaserver.domain.post.service;

import com.command.itdaserver.domain.post.domain.Post;
import com.command.itdaserver.domain.post.domain.Question;
import com.command.itdaserver.domain.post.domain.QuestionOption;
import com.command.itdaserver.domain.post.domain.enums.AnswerType;
import com.command.itdaserver.domain.post.domain.repository.PostRepository;
import com.command.itdaserver.domain.post.exceptions.PostNotFoundException;
import com.command.itdaserver.domain.post.presentation.dto.request.CreateFormRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CreateApplyFormService {

    private final PostRepository postRepository;

    @Transactional
    public List<Question> execute(Long postId, CreateFormRequest request) {

        // 반환을 위해 저장할 Question 리스트
        List<Question> questions = new ArrayList<>();

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        for (var qDto : request.getQuestions()) {
            Question question = Question.builder()
                    .questionNumber(qDto.getQuestionNumber())
                    .questionContent(qDto.getQuestionContent())
                    .answerType(qDto.getAnswerType())
                    .multiple(qDto.getMultiple())
                    .required(qDto.getRequired())
                    .build();
            if (qDto.getAnswerType() == AnswerType.OBJECTIVE) { // 객관식인 경우 옵션 추가
                for (var optDto : qDto.getOptions()) {
                    QuestionOption option = QuestionOption.builder()
                            .answerNumber(optDto.getAnswerNumber())
                            .answerContent(optDto.getAnswerContent())
                            .build();
                    question.addOptions(option); // Cascade 이기 때문에 DB 저장됨
                }
            }
            post.addQuestion(question); // Cascade 이기 때문에 DB 저장됨
            questions.add(question);
        }
        return  questions;
    }
}
