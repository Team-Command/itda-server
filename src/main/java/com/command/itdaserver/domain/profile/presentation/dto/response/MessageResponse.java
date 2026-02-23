package com.command.itdaserver.domain.profile.presentation.dto.response;

public record MessageResponse(String message) {

    public static MessageResponse of(String message) {
        return new MessageResponse(message);
    }
}
