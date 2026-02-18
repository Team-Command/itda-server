package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class DuplicateAnswerException extends ItdaException {
    public static final ItdaException EXCEPTION = new DuplicateAnswerException();
    public DuplicateAnswerException() {
        super(ErrorCode.DUPLICATE_ANSWER);
    }
}