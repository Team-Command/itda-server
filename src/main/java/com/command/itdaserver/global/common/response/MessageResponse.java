package com.command.itdaserver.global.common.response;

public record MessageResponse(String message) {

    public static MessageResponse of(String message) {
        return new MessageResponse(message);
    }
}
