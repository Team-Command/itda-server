package com.command.itdaserver.domain.auth.presentation.dto.request;

public record ChangePasswordRequest(
        String resetToken,
        String newPassword
) {
}
