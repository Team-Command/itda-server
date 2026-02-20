package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class QuestionNotFoundException extends ItdaException {
    public static final ItdaException EXCEPTION = new QuestionNotFoundException();
    public QuestionNotFoundException() {
        super(ErrorCode.QUESTION_NOT_FOUND);
    }
}
