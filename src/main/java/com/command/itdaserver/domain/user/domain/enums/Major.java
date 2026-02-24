package com.command.itdaserver.domain.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Major {
    FRONT_END("프론트엔드"),
    BACK_END("백엔드"),
    APP("앱"),
    ETC("기타"), // 선택 시 customMajor 입력 필요함
    FALSE("false"); // 전공을 비공개로 하였을때 보내는 값

    private final String displayName;
}
