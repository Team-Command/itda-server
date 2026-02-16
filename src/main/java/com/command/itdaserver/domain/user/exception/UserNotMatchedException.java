package com.command.itdaserver.domain.user.exception;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class UserNotMatchedException extends ItdaException {
    public static final ItdaException EXCEPTION = new UserNotMatchedException();

    public UserNotMatchedException() {
        super(ErrorCode.USER_NOT_MATCHED);
    }
}
