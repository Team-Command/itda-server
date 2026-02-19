package com.command.itdaserver.domain.auth.exception;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class VerificationCodeNotMatchedException extends ItdaException {
    public static final ItdaException EXCEPTION = new VerificationCodeNotMatchedException();

    private VerificationCodeNotMatchedException() {
        super(ErrorCode.VERIFICATION_CODE_NOT_MATCHED);
    }
}
