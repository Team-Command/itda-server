package com.command.itdaserver.domain.user.presentation.dto.request;

public record UserPublicProfileRequest(
        boolean name,
        boolean email,
        boolean major,
        boolean customMajor,
        boolean grade,
        boolean school
) {
}
