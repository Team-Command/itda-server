package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class UnauthorizedApplicationAccessException extends ItdaException {
    public static final ItdaException EXCEPTION = new UnauthorizedApplicationAccessException();
    public UnauthorizedApplicationAccessException() {
        super(ErrorCode.UNAUTHORIZED_APPLICATION_ACCESS);
    }
}
