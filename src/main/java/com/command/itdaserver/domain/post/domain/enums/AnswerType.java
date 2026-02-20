package com.command.itdaserver.domain.post.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnswerType {

    SUBJECTIVE("주관식"),
    OBJECTIVE("객관식");

    private final String displayName;
}
