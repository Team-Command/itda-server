package com.command.itdaserver.domain.post.presentation.dto.response;

import com.command.itdaserver.domain.post.domain.Answer;
import com.command.itdaserver.domain.post.domain.Application;
import com.command.itdaserver.domain.post.domain.enums.AnswerType;
import com.command.itdaserver.domain.post.domain.enums.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ApplicationDetailResponse(
        Long applicationId,
        String applicantId,
        String applicantName,
        ApplicationStatus status,
        LocalDateTime appliedAt,
        List<AnswerDetailDto> answers
) {
    public static ApplicationDetailResponse from(Application application) {
        return new ApplicationDetailResponse(
                application.getId(),
                application.getApplicant().getUserId(),
                application.getApplicant().getName(),
                application.getStatus(),
                application.getAppliedAt(),
                application.getAnswers().stream()
                        .map(AnswerDetailDto::from)
                        .toList()
        );
    }

    public record AnswerDetailDto(
            Long questionId,
            String questionContent,
            AnswerType answerType,
            List<SelectedOptionDto> selectedOptions,
            String textAnswer
    ) {
        public static AnswerDetailDto from(Answer answer) {
            if (answer.getQuestion().getAnswerType() == AnswerType.OBJECTIVE) {
                return new AnswerDetailDto(
                        answer.getQuestion().getId(),
                        answer.getQuestion().getQuestionContent(),
                        AnswerType.OBJECTIVE,
                        answer.getSelectedOption() != null
                                ? List.of(new SelectedOptionDto(
                                        answer.getSelectedOption().getAnswerNumber(),
                                        answer.getSelectedOption().getAnswerContent()))
                                : List.of(),
                        null
                );
            } else {
                return new AnswerDetailDto(
                        answer.getQuestion().getId(),
                        answer.getQuestion().getQuestionContent(),
                        AnswerType.SUBJECTIVE,
                        null,
                        answer.getTextAnswer()
                );
            }
        }
    }

    public record SelectedOptionDto(
            Integer answerNumber,
            String answerContent
    ) {}
}
