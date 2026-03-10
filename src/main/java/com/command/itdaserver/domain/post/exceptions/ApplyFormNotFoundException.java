package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class ApplyFormNotFoundException extends ItdaException {
    public static final ItdaException EXCEPTION = new ApplyFormNotFoundException();
    public ApplyFormNotFoundException() {
        super(ErrorCode.APPLY_FORM_NOT_FOUND);
    }
}
