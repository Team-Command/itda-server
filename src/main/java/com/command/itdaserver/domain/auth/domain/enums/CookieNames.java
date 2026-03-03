package com.command.itdaserver.domain.auth.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CookieNames {
    SESSION_ID("SESSION_ID"),
    REMEMBER_ME("REMEMBER_ME");

    private final String name;
}
