package com.command.itdaserver.domain.user.exception;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class UserNotFoundException extends ItdaException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
