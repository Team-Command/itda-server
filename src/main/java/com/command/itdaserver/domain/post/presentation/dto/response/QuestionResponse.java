package com.command.itdaserver.domain.post.presentation.dto.response;

import com.command.itdaserver.domain.post.domain.Question;
import com.command.itdaserver.domain.post.domain.QuestionOption;
import com.command.itdaserver.domain.post.domain.enums.AnswerType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionResponse {
    private Integer questionNumber;
    private String questionContent;
    private AnswerType answerType;
    private boolean multiple;
    private boolean required;
    private List<QuestionOption> options = new ArrayList<>();

    public QuestionResponse(Question question) {
        this.questionNumber = question.getQuestionNumber();
        this.questionContent = question.getQuestionContent();
        this.answerType = question.getAnswerType();
        this.multiple = question.isMultiple();
        this.required = question.isRequired();
        this.options = question.getOptions();
    }
}
