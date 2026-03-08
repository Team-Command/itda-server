package com.command.itdaserver.domain.profile.presentation.dto.response;

import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.UserDisclosure;
import com.command.itdaserver.domain.user.domain.enums.Grade;
import com.command.itdaserver.domain.user.domain.enums.Major;
import com.command.itdaserver.domain.user.domain.enums.School;
import com.fasterxml.jackson.annotation.JsonInclude;

public record UserPublicProfileResponse(
        String userImage,
        String userId,
        String name,
        String email,
        Major major,
        String customMajor,
        Grade grade,
        School school
) {
    public static UserPublicProfileResponse from(User user, UserDisclosure disclosure) {
        return new UserPublicProfileResponse(
                user.getUserImage(),
                user.getUserId(),
                disclosure.isNamePublic() ? user.getName() : null,
                disclosure.isEmailPublic() ? user.getEmail() : null,
                user.getMajor(),
                user.getCustomMajor(),
                disclosure.isGradePublic() ? user.getGrade() : null,
                user.getSchool()
        );
    }
}
