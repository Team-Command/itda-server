package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class InvalidDeadlineException extends ItdaException {
    public static final ItdaException EXCEPTION = new InvalidDeadlineException();

    public InvalidDeadlineException() {
        super(ErrorCode.INVALID_DEADLINE);
    }
}
