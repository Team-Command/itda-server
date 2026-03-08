package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class UnauthorizedCommentAccessException extends ItdaException {
    public static final ItdaException EXCEPTION = new UnauthorizedCommentAccessException();
    public UnauthorizedCommentAccessException() {
        super(ErrorCode.UNAUTHORIZED_COMMENT_ACCESS);
    }
}
