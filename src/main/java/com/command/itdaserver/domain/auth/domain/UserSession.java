package com.command.itdaserver.domain.auth.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "session") // Redis Key: "session:{sessionId}"
public class UserSession {

    @Id
    private String sessionId; // UUID로 생성한 세션 ID

    private Long userPk;

    @Indexed
    private String userId;

    private String email;

    private String role;

    @TimeToLive
    private int expiration; // TTL (초 단위) - 예: 1800 (30분)

    public static UserSession create(Long userPk, String userId, String email, String role, int expiration) {
        return UserSession.builder()
                .userPk(userPk)
                .sessionId(UUID.randomUUID().toString()) // 랜덤 세션 ID
                .userId(userId)
                .email(email)
                .role(role)
                .expiration(expiration)
                .build();
    }
}
