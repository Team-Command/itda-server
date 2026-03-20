package com.command.itdaserver.domain.chat.domain;

import com.command.itdaserver.domain.user.domain.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private ChatRoom room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String lastReadMessage;

    public static ChatRoomUser of(ChatRoom room,  User user, String lastReadMessage) {
        return ChatRoomUser.builder()
                .room(room)
                .user(user)
                .lastReadMessage(lastReadMessage)
                .build();
    }
}
