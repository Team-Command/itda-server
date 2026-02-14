package com.command.itdaserver.domain.user.exception;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class UserNotFoundException extends ItdaException {
    public static final ItdaException EXCEPTION = new UserNotFoundException();

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
