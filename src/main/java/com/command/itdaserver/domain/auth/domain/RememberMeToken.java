package com.command.itdaserver.domain.auth.domain;


import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@RedisHash(value = "remember_me", timeToLive = 604800)
public class RememberMeToken {
    @Id
    private String token;

    @Indexed
    private String userId;

    private LocalDateTime createdAt;

    public static RememberMeToken create(String userId) {
        RememberMeToken token = new RememberMeToken();
        token.token = UUID.randomUUID().toString();
        token.userId = userId;
        token.createdAt = LocalDateTime.now();
        return token;
    }


}
