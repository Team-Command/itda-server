package com.command.itdaserver.domain.post.presentation.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubmitAnswerRequest {

    @Valid
    @NotEmpty(message = "답변 목록은 비어있을 수 없습니다.")
    private List<AnswerDto> answers = new ArrayList<>();

    @Data
    public static class AnswerDto {

        @NotNull
        private Long questionId;

        private List<Long> selectedOptionIds; // OBJECTIVE

        private String textAnswer; // SUBJECTIVE
    }
}