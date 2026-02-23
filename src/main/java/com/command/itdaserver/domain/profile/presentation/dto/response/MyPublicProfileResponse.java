package com.command.itdaserver.domain.profile.presentation.dto.response;

import com.command.itdaserver.domain.user.domain.UserDisclosure;

public record MyPublicProfileResponse(
        boolean isNamePublic,
        boolean isEmailPublic,
        boolean isMajorPublic,
        boolean isCustomMajorPublic,
        boolean isGradePublic,
        boolean isSchoolPublic
) {
    public static MyPublicProfileResponse of(UserDisclosure userDisclosure) {
        return new MyPublicProfileResponse(
                userDisclosure.isNamePublic(),
                userDisclosure.isEmailPublic(),
                userDisclosure.isMajorPublic(),
                userDisclosure.isCustomMajorPublic(),
                userDisclosure.isGradePublic(),
                userDisclosure.isSchoolPublic()
        );
    }
}
