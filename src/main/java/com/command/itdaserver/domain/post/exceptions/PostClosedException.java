package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class PostClosedException extends ItdaException {
    public static final ItdaException EXCEPTION = new PostClosedException();
    public PostClosedException() {
        super(ErrorCode.POST_CLOSED);
    }
}
