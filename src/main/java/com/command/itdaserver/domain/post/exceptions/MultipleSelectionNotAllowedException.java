package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class MultipleSelectionNotAllowedException extends ItdaException {
    public static final ItdaException EXCEPTION = new MultipleSelectionNotAllowedException();
    public MultipleSelectionNotAllowedException() {
        super(ErrorCode.MULTIPLE_SELECTION_NOT_ALLOWED);
    }
}