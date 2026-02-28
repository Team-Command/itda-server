package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class DuplicateOptionSelectException extends ItdaException {
    public static final ItdaException EXCEPTION = new DuplicateOptionSelectException();
    public DuplicateOptionSelectException() {
        super(ErrorCode.DUPLICATE_OPTION_SELECT);
    }
}
