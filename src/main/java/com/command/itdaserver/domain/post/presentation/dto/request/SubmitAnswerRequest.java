package com.command.itdaserver.domain.post.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubmitAnswerRequest {

    private List<AnswerDto> answers = new ArrayList<>();

    @Data
    public static class AnswerDto {

        @NotNull
        private Long questionId;

        private List<Long> selectedOptionIds; // OBJECTIVE

        private String textAnswer; // SUBJECTIVE
    }
}