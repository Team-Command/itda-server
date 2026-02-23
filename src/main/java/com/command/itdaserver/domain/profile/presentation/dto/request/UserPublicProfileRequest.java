package com.command.itdaserver.domain.profile.presentation.dto.request;

public record UserPublicProfileRequest(
        boolean isNamePublic,
        boolean isEmailPublic,
        boolean isMajorPublic,
        boolean isCustomMajorPublic,
        boolean isGradePublic,
        boolean isSchoolPublic
) {
}
