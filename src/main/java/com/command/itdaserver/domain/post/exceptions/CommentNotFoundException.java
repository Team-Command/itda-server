package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class CommentNotFoundException extends ItdaException {
    public static final ItdaException EXCEPTION = new CommentNotFoundException();
    public CommentNotFoundException() {
        super(ErrorCode.COMMENT_NOT_FOUND);
    }
}
