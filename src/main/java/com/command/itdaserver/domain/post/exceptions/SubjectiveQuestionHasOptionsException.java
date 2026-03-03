package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class SubjectiveQuestionHasOptionsException extends ItdaException {
    public static final ItdaException EXCEPTION = new SubjectiveQuestionHasOptionsException();
    public SubjectiveQuestionHasOptionsException() {
        super(ErrorCode.SUBJECTIVE_QUESTION_HAS_OPTIONS);
    }
}
