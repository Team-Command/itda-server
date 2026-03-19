package com.command.itdaserver.domain.chat.presentaion.dto.response;

import com.command.itdaserver.domain.user.domain.User;

public record ChatUserResponse(
        Long userId,
        String name,
        String userImage
) {
    public static ChatUserResponse from(User user) {
        return new ChatUserResponse(
                user.getId(),
                user.getName(),
                user.getUserImage()
        );
    }
}