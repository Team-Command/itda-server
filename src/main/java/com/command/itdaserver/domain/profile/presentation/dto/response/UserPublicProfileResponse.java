package com.command.itdaserver.domain.profile.presentation.dto.response;

import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.UserDisclosure;
import com.command.itdaserver.domain.user.domain.enums.Grade;
import com.command.itdaserver.domain.user.domain.enums.Major;
import com.command.itdaserver.domain.user.domain.enums.School;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserPublicProfileResponse(
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
                user.getUserId(),
                disclosure.isNamePublic() ? user.getName() : null,
                disclosure.isEmailPublic() ? user.getEmail() : null,
                disclosure.isMajorPublic() ? user.getMajor() : null,
                disclosure.isMajorPublic() ? user.getCustomMajor() : null,
                disclosure.isGradePublic() ? user.getGrade() : null,
                disclosure.isSchoolPublic() ? user.getSchool() : null
        );
    }
}
