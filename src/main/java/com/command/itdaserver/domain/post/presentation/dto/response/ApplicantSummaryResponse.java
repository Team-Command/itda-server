package com.command.itdaserver.domain.post.presentation.dto.response;

import com.command.itdaserver.domain.post.domain.Application;
import com.command.itdaserver.domain.post.domain.enums.ApplicationStatus;

import java.time.LocalDateTime;

public record ApplicantSummaryResponse(
        Long applicationId,
        String applicantId,
        String applicantName,
        ApplicationStatus status,
        LocalDateTime appliedAt
) {
    public static ApplicantSummaryResponse from(Application application) {
        return new ApplicantSummaryResponse(
                application.getId(),
                application.getApplicant().getUserId(),
                application.getApplicant().getName(),
                application.getStatus(),
                application.getAppliedAt()
        );
    }
}
