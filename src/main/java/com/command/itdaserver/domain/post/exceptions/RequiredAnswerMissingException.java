package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class RequiredAnswerMissingException extends ItdaException {
    public static final ItdaException EXCEPTION = new RequiredAnswerMissingException();
    public RequiredAnswerMissingException() {
        super(ErrorCode.REQUIRED_ANSWER_MISSING);
    }
}