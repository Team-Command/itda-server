package com.command.itdaserver.domain.chat.domain.repository;

import com.command.itdaserver.domain.chat.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByRoomName(String roomName);
}
