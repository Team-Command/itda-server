package com.command.itdaserver.domain.auth.service.command;

import com.command.itdaserver.domain.auth.presentation.dto.request.SignUpRequest;
import com.command.itdaserver.domain.user.domain.enums.Grade;
import com.command.itdaserver.domain.user.domain.enums.Major;
import com.command.itdaserver.domain.user.domain.enums.School;

public record SignUpCommand(
        String name,
        String userId,
        String password,
        String email,
        Major major,
        String customMajor,
        School school,
        Grade grade
        ) {

    public static SignUpCommand from(SignUpRequest request) {
        return new SignUpCommand(
                request.name(),
                request.userId(),
                request.password(),
                request.email(),
                request.major(),
                request.customMajor(),
                request.school(),
                request.grade()
        );
    }

}
