package com.command.itdaserver.domain.auth.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "remember_me", timeToLive = 604800)
public class RememberMeToken {
    @Id
    private String token;

    @Indexed
    private String userId;

    private LocalDateTime createdAt;

    public static RememberMeToken create(String userId) {
        return new RememberMeToken(
                UUID.randomUUID().toString(),
                userId,
                LocalDateTime.now()
        );
    }
}
