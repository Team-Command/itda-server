package com.command.itdaserver.domain.auth.presentation.dto.request;

public record VerifyEmailCodeRequest(
        String email,
        String code
) {
}
