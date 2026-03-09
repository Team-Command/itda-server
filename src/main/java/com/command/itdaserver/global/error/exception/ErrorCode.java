package com.command.itdaserver.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    // profile
    USER_DISCLOSURE_NOT_FOUND(404, "해당 유저에 관한 공개유무를 조회할 수 없습니다."),

    //user
    INVALID_USER(401, "유효 하지 않은 사용자입니다."),
    INVALID_CUSTOM_MAJOR(400, "기타 전공 선택 시 전공명을 입력해야 합니다."),
    USER_NOT_FOUND(404, "존재하지 않는 사용자입니다."),

    // auth - 새로 추가!
    DUPLICATE_USER_ID(409, "이미 존재하는 아이디입니다."),
    DUPLICATE_EMAIL(409, "이미 가입된 이메일입니다."),
    USER_NOT_MATCHED(401, "아이디 또는 비밀번호를 확인해주세요."),
    VERIFICATION_CODE_EXPIRED(404, "인증 코드가 만료되었습니다."),
    VERIFICATION_CODE_NOT_MATCHED(400, "인증 코드가 일치하지 않습니다."),
    RESET_TOKEN_NOT_FOUND(401, "유효하지 않은 비밀번호 재설정 토큰입니다."),
    EMAIL_NOT_MATCHED(400, "요청의 이메일이 인증된 사용자의 이메일과 일치하지 않습니다."),

    // post
    INVALID_MAJOR(400, "존재하지 않는 전공입니다."),
    INVALID_DEADLINE(400, "마감 기간이 현재 기간보다 이전입니다."),
    POST_NOT_FOUND(404, "해당 게시글이 존재하지 않습니다."),
    MISSING_QUESTION_OPTION(400, "객관식 질문에 보기가 없습니다."),
    MISSING_SELECTED_OPTION(400, "객관식 질문에 선택한 옵션이 없습니다."),
    QUESTION_NOT_FOUND(404, "해당 질문이 존재하지 않습니다."),
    INVALID_QUESTION_OPTION(400, "해당 질문에 존재하지 않는 옵션입니다."),
    DUPLICATE_ANSWER(409, "이미 해당 질문에 답변을 제출하셨습니다."),
    REQUIRED_ANSWER_MISSING(400, "필수 질문에 답변이 없습니다."),
    MULTIPLE_SELECTION_NOT_ALLOWED(400, "단일 선택만 가능한 질문입니다."),
    UNAUTHORIZED_POST_ACCESS(403, "해당 게시글에 대한 권한이 없습니다."),
    POST_CLOSED(400, "모집이 마감된 게시글입니다."),
    APPLY_FORM_ALREADY_EXISTS(409, "이미 지원 폼이 존재합니다."),
    DUPLICATE_OPTION_SELECT(400, "중복된 선택지가 포함되어 있습니다."),
    SUBJECTIVE_QUESTION_HAS_OPTIONS(400, "주관식 질문에는 보기를 추가할 수 없습니다."),

    // application
    APPLICATION_NOT_FOUND(404, "해당 지원서가 존재하지 않습니다."),
    DUPLICATE_APPLICATION(409, "이미 해당 게시글에 지원하셨습니다."),
    UNAUTHORIZED_APPLICATION_ACCESS(403, "해당 지원서에 대한 권한이 없습니다."),

    // comment
    COMMENT_NOT_FOUND(404, "해당 댓글이 존재하지 않습니다."),
    UNAUTHORIZED_COMMENT_ACCESS(403, "해당 댓글에 대한 권한이 없습니다."),
    CANNOT_REPLY_TO_REPLY(400, "답글에는 답글을 달 수 없습니다."),

    // general
    BAD_REQUEST(400, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(500, "내부 서버 오류가 발생했습니다.");

    private final int statusCode;
    private final String errorMessage;
}
