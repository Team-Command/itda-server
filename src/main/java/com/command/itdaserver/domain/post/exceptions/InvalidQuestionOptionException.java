package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class InvalidQuestionOptionException extends ItdaException {
    public static final ItdaException EXCEPTION = new InvalidQuestionOptionException();
    public InvalidQuestionOptionException() {
        super(ErrorCode.INVALID_QUESTION_OPTION);
    }
}