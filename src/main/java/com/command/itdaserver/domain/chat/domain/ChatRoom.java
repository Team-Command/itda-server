package com.command.itdaserver.domain.chat.domain;

import com.command.itdaserver.global.entity.BaseIdEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chat_room")
@AttributeOverride(name = "id", column = @Column(name = "cr_id"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ChatRoom extends BaseIdEntity {
    @Column(nullable = false)
    private String roomName;
}
