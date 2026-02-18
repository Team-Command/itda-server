package com.command.itdaserver.domain.auth.exception;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class VerificationCodeExpiredException extends ItdaException {
    public static final ItdaException EXCEPTION = new VerificationCodeExpiredException();

    private VerificationCodeExpiredException() {
        super(ErrorCode.VERIFICATION_CODE_EXPIRED);
    }
}
