package com.command.itdaserver.domain.chat.domain.repository;

import com.command.itdaserver.domain.chat.domain.ChatRoom;
import com.command.itdaserver.domain.chat.domain.ChatRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Long> {
    @Query("SELECT cru.room FROM ChatRoomUser cru " +
            "WHERE cru.user.id IN :ids " +
            "GROUP BY cru.room " +
            "HAVING COUNT(cru.room) = :idCount " +
            "AND (SELECT COUNT(cru2) FROM ChatRoomUser cru2 WHERE cru2.room = cru.room) = :idCount")
    Optional<ChatRoom> findChatRoomByUsers(@Param("ids") List<Long> ids, @Param("idCount") Long idCount);
}