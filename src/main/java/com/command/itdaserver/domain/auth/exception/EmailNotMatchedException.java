package com.command.itdaserver.domain.auth.exception;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class EmailNotMatchedException extends ItdaException {
    public static final ItdaException EXCEPTION = new EmailNotMatchedException();

    public EmailNotMatchedException() {
        super(ErrorCode.EMAIL_NOT_MATCHED);
    }
}