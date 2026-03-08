package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class UnauthorizedPostAccessException extends ItdaException {
    public static final ItdaException EXCEPTION = new UnauthorizedPostAccessException();
    public UnauthorizedPostAccessException() {
        super(ErrorCode.UNAUTHORIZED_POST_ACCESS);
    }
}
