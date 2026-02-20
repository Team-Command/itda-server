package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class MissingQuestionOptionException extends ItdaException {
    public static final ItdaException EXCEPTION = new MissingQuestionOptionException();
    public MissingQuestionOptionException() {
        super(ErrorCode.MISSING_QUESTION_OPTION);
    }
}
