package com.command.itdaserver.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    //user
    INVALID_USER(401, "유효 하지 않은 사용자입니다."),
    INVALID_CUSTOM_MAJOR(400, "기타 전공 선택 시 전공명을 입력해야 합니다."),

    // auth - 새로 추가!
    DUPLICATE_USER_ID(409, "이미 존재하는 아이디입니다."),
    DUPLICATE_EMAIL(409, "이미 가입된 이메일입니다."),
    USER_NOT_MATCHED(401, "아이디 또는 비밀번호를 확인해주세요."),

    // general
    BAD_REQUEST(400, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(500, "내부 서버 오류가 발생했습니다.");

    private final int statusCode;
    private final String errorMessage;
}
