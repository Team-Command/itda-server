package com.command.itdaserver.domain.profile.exception;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class UserIdDuplicateException extends ItdaException {
    public static final ItdaException EXCEPTION = new UserIdDuplicateException();

    public UserIdDuplicateException() {
        super(ErrorCode.DUPLICATE_USER_ID);
    }
}
