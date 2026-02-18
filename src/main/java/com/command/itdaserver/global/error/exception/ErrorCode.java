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
    PASSWORD_MISMATCH(401, "비밀번호가 일치 하지 않습니다."),
    INVALID_USER(401, "유효 하지 않은 사용자입니다."),
    USER_EXIST(401, "유저가 이미 존재합니다."),
    COMPANION_NOT_FOUND(404, "존재하지 않는 동행인 ID입니다."),
    USER_ACCESS_DENIED(403, "해당 유저에 관한 권한이 없습니다."),
    INVALID_CUSTOM_MAJOR(400, "기타 전공 선택 시 전공명을 입력해야 합니다."),

    // auth - 새로 추가!
    DUPLICATE_USER_ID(409, "이미 존재하는 아이디입니다."),
    DUPLICATE_EMAIL(409, "이미 가입된 이메일입니다."),

    // post
    INVALID_DEADLINE(400, "마감 시간이 현재 시간보다 이전입니다."),
    POST_NOT_FOUND(404, "해당 게시글이 존재하지 않습니다."),
    INVALID_OBJECT_QUESTION(400, "객관식 질문이나, 보기가 존재하지 않습니다."),

    // general
    BAD_REQUEST(400, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(500, "내부 서버 오류가 발생했습니다.");

    private final int statusCode;
    private final String errorMessage;
}
