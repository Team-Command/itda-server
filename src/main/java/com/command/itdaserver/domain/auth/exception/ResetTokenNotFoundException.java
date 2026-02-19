package com.command.itdaserver.domain.auth.exception;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class ResetTokenNotFoundException extends ItdaException {
    public static final ItdaException EXCEPTION = new ResetTokenNotFoundException();

    public ResetTokenNotFoundException() {
        super(ErrorCode.RESET_TOKEN_NOT_FOUND);
    }
}
