package com.command.itdaserver.domain.user.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Grade {
    FIRST(1, "1학년"),
    SECOND(2, "2학년"),
    THIRD(3, "3학년");

    private final int gradeNumber;
    private final String displayName;
}