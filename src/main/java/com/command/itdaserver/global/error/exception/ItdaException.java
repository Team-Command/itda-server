package com.command.itdaserver.global.error.exception;

import lombok.Getter;

@Getter
public class ItdaException extends RuntimeException {
    private final ErrorCode errorCode;
    public ItdaException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
    public ItdaException(ErrorCode errorCode, String customMessage) {
        super(customMessage);
        this.errorCode = errorCode;
    }
}
