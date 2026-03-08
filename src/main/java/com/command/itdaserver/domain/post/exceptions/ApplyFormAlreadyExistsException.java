package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class ApplyFormAlreadyExistsException extends ItdaException {
    public static final ItdaException EXCEPTION = new ApplyFormAlreadyExistsException();
    public ApplyFormAlreadyExistsException() {
        super(ErrorCode.APPLY_FORM_ALREADY_EXISTS);
    }
}
