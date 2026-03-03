package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class CannotReplyToReplyException extends ItdaException {
    public static final ItdaException EXCEPTION = new CannotReplyToReplyException();
    public CannotReplyToReplyException() {
        super(ErrorCode.CANNOT_REPLY_TO_REPLY);
    }
}
