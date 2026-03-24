package com.command.itdaserver.domain.chat.presentaion.dto.response;

import java.util.List;

public record FirstChatRoomResponse(
        Long roomId,
        String roomName,
        List<String> roomImageUrl,
        List<ChatUserResponse> chatUsers
) {
    public static FirstChatRoomResponse of(Long roomId, String roomName, List<String> roomImage, List<ChatUserResponse> chatUsers) {
        return new FirstChatRoomResponse(
                roomId,
                roomName,
                roomImage,
                chatUsers
        );
    }
}
