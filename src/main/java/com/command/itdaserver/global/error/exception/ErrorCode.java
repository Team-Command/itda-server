package com.command.itdaserver.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    //user
    USER_NOT_FOUND(404, "해당 유저가 존재 하지 않습니다."),
    USER_MISMATCH(401, "유저가 일치 하지 않습니다."),
    PASSWORD_MISMATCH(401, "비밀 번호가 일치 하지 않습니다."),
    INVALID_USER(401, "유효 하지 않은 사용자입니다."),
    USER_EXIST(401, "유저가 이미 존재합니다."),
    COMPANION_NOT_FOUND(404, "존재하지 않는 동행인 ID입니다."),
    USER_ACCESS_DENIED(403, "해당 유저에 관한 권한이 없습니다."),

    // general
    BAD_REQUEST(400, "프론트 탓"),
    INTERNAL_SERVER_ERROR(500, "서버 탓");

    private final int statusCode;
    private final String errorMessage;
}
