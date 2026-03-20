package com.command.itdaserver.domain.chat.domain.repository;

import com.command.itdaserver.domain.chat.domain.ChatRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Long> {
}
