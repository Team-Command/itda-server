package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class DuplicateApplicationException extends ItdaException {
    public static final ItdaException EXCEPTION = new DuplicateApplicationException();
    public DuplicateApplicationException() {
        super(ErrorCode.DUPLICATE_APPLICATION);
    }
}
