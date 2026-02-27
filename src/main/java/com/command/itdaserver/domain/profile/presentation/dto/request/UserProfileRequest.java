package com.command.itdaserver.domain.profile.presentation.dto.request;

import com.command.itdaserver.domain.user.domain.enums.Grade;
import com.command.itdaserver.domain.user.domain.enums.Major;
import com.command.itdaserver.domain.user.domain.enums.School;

public record UserProfileRequest(
        String imageUrl,
        String name,
        String userId,
        String email,
        Major major,
        String customMajor,
        School school,
        Grade grade
) {
}
