package com.command.itdaserver.domain.auth.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@RedisHash(value = "session") // Redis Key: "session:{sessionId}"
public class UserSession {

    @Id
    private String sessionId; // UUID로 생성한 세션 ID

    private String userId;
    private String email;
    private String role;

    @TimeToLive
    private Long expiration; // TTL (초 단위) - 예: 1800 (30분)

    public static UserSession create(String userId, String email, String role, Long expiration) {
        return UserSession.builder()
                .sessionId(UUID.randomUUID().toString()) // 랜덤 세션 ID
                .userId(userId)
                .email(email)
                .role(role)
                .expiration(expiration)
                .build();
    }
}
