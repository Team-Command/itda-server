package com.command.itdaserver.domain.profile.presentation.dto.response;

import com.command.itdaserver.domain.user.domain.UserDisclosure;

public record UserProfileDisclosureResponse(
        boolean isNamePublic,
        boolean isEmailPublic,
        boolean isMajorPublic,
        boolean isCustomMajorPublic,
        boolean isGradePublic,
        boolean isSchoolPublic
) {
    public static UserProfileDisclosureResponse of(UserDisclosure userDisclosure) {
        return new UserProfileDisclosureResponse(
                userDisclosure.isNamePublic(),
                userDisclosure.isEmailPublic(),
                userDisclosure.isMajorPublic(),
                userDisclosure.isCustomMajorPublic(),
                userDisclosure.isGradePublic(),
                userDisclosure.isSchoolPublic()
        );
    }
}
