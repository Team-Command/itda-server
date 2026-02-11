package com.command.itdaserver.domain.auth.exception;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class DuplicateUserIdException extends ItdaException {
    public static final ItdaException EXCEPTION = new DuplicateEmailException();

    public DuplicateUserIdException() {
        super(ErrorCode.DUPLICATE_USER_ID);
    }
}
