package com.command.itdaserver.domain.post.exceptions;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class PostNotFoundException extends ItdaException {
    public static final ItdaException EXCEPTION = new PostNotFoundException();
    public PostNotFoundException() {
        super(ErrorCode.POST_NOT_FOUND);
    }
}
