package com.command.itdaserver.domain.chat.domain;

import com.command.itdaserver.global.entity.BaseIdEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chat_user")
@AttributeOverride(name = "id", column = @Column(name = "cu_id"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ChatRoomUser extends BaseIdEntity {

    @Column(name = "room_id", nullable = false, unique = true)
    private String roomId;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String lastReadMessage;

    public static ChatRoomUser of(String roomId, String userId, String lastReadMessage) {
        return ChatRoomUser.builder()
                .roomId(roomId)
                .userId(userId)
                .lastReadMessage(lastReadMessage)
                .build();
    }
}
