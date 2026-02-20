package com.command.itdaserver.domain.post.presentation.dto.response;

import com.command.itdaserver.domain.post.domain.Question;
import com.command.itdaserver.domain.post.domain.enums.AnswerType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class AnswerResponse {

    private Long questionId;
    private String questionContent;
    private AnswerType answerType;
    private List<SelectedOptionDto> selectedOptions; // SUBJECTIVE면 null
    private String textAnswer; // OBJECTIVE면 null

    public AnswerResponse(Question question, List<SelectedOptionDto> selectedOptions) {
        this.questionId = question.getId();
        this.questionContent = question.getQuestionContent();
        this.answerType = AnswerType.OBJECTIVE;
        this.selectedOptions = selectedOptions;
        this.textAnswer = null;
    }

    public AnswerResponse(Question question, String textAnswer) {
        this.questionId = question.getId();
        this.questionContent = question.getQuestionContent();
        this.answerType = AnswerType.SUBJECTIVE;
        this.selectedOptions = null;
        this.textAnswer = textAnswer;
    }

    @Data
    @AllArgsConstructor
    public static class SelectedOptionDto {
        private Integer answerNumber;
        private String answerContent;
    }
}