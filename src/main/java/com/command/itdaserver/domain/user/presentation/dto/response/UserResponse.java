package com.command.itdaserver.domain.user.presentation.dto.response;

import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.enums.Grade;
import com.command.itdaserver.domain.user.domain.enums.Major;
import com.command.itdaserver.domain.user.domain.enums.School;

public record UserResponse(
        String userImage, // 추후 s3 도입 후 리펙터링 예정
        String name,
        String userId,
        String email,
        Major major,
        String customMajor,
        School school,
        Grade grade
) {

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getUserImage(), // 추후 s3 도입 후 리펙터링 예정
                user.getName(),
                user.getUserId(),
                user.getEmail(),
                user.getMajor(),
                user.getCustomMajor(),
                user.getSchool(),
                user.getGrade()
        );
    }
}
