package com.command.itdaserver.domain.chat.domain.repository;

import com.command.itdaserver.domain.chat.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
