package com.command.itdaserver.domain.post.presentation.dto.response;

import com.command.itdaserver.domain.post.domain.Answer;
import com.command.itdaserver.domain.post.domain.Application;
import com.command.itdaserver.domain.post.domain.Question;
import com.command.itdaserver.domain.post.domain.enums.AnswerType;
import com.command.itdaserver.domain.post.domain.enums.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record ApplicationDetailResponse(
        Long applicationId,
        String applicantId,
        String applicantName,
        ApplicationStatus status,
        LocalDateTime appliedAt,
        List<AnswerDetailDto> answers
) {
    public static ApplicationDetailResponse from(Application application) {
        Map<Long, List<Answer>> answersByQuestionId = application.getAnswers().stream()
                .collect(Collectors.groupingBy(a -> a.getQuestion().getId()));

        List<AnswerDetailDto> answerDtos = answersByQuestionId.entrySet().stream()
                .sorted(java.util.Comparator.comparing(entry -> entry.getValue().get(0).getQuestion().getQuestionNumber()))
                .map(entry -> {
                    List<Answer> answers = entry.getValue();
                    Question question = answers.get(0).getQuestion();
                    return AnswerDetailDto.from(question, answers);
                })
                .toList();

        return new ApplicationDetailResponse(
                application.getId(),
                application.getApplicant().getUserId(),
                application.getApplicant().getName(),
                application.getStatus(),
                application.getAppliedAt(),
                answerDtos
        );
    }

    public record AnswerDetailDto(
            Long questionId,
            String questionContent,
            AnswerType answerType,
            List<SelectedOptionDto> selectedOptions,
            String textAnswer
    ) {
        public static AnswerDetailDto from(Question question, List<Answer> answers) {
            if (question.getAnswerType() == AnswerType.OBJECTIVE) {
                List<SelectedOptionDto> selectedOptions = answers.stream()
                        .filter(a -> a.getSelectedOption() != null)
                        .map(a -> new SelectedOptionDto(
                                a.getSelectedOption().getAnswerNumber(),
                                a.getSelectedOption().getAnswerContent()))
                        .toList();
                return new AnswerDetailDto(
                        question.getId(),
                        question.getQuestionContent(),
                        AnswerType.OBJECTIVE,
                        selectedOptions,
                        null
                );
            } else {
                return new AnswerDetailDto(
                        question.getId(),
                        question.getQuestionContent(),
                        AnswerType.SUBJECTIVE,
                        null,
                        answers.isEmpty() ? null : answers.get(0).getTextAnswer()
                );
            }
        }
    }

    public record SelectedOptionDto(
            Integer answerNumber,
            String answerContent
    ) {}
}
