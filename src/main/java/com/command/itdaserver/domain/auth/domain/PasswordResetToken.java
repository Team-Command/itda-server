package com.command.itdaserver.domain.auth.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "password_reset_token")
public class PasswordResetToken {

    @Id
    private String token; //uuid

    private String email;

    @TimeToLive
    private int expiration;

    public static PasswordResetToken create(String email) {
        return PasswordResetToken.builder()
                .token(UUID.randomUUID().toString())
                .email(email)
                .expiration(600) // 10분
                .build();
    }
}
