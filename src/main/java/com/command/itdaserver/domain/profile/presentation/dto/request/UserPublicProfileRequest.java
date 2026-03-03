package com.command.itdaserver.domain.profile.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserPublicProfileRequest(

        @JsonProperty("name_view")
        boolean isNamePublic,

        @JsonProperty("email_view")
        boolean isEmailPublic,

        @JsonProperty("major_view")
        boolean isMajorPublic,

        @JsonProperty("custom_major_view")
        boolean isCustomMajorPublic,

        @JsonProperty("grade_view")
        boolean isGradePublic,

        @JsonProperty("school_view")
        boolean isSchoolPublic
) {
}
