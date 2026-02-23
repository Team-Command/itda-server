package com.command.itdaserver.domain.profile.exception;

import com.command.itdaserver.global.error.exception.ErrorCode;
import com.command.itdaserver.global.error.exception.ItdaException;

public class UserDisclosureNotFoundException extends ItdaException {
    public static final ItdaException EXCEPTION = new UserDisclosureNotFoundException();

    public UserDisclosureNotFoundException() {
        super(ErrorCode.USER_DISCLOSURE_NOT_FOUND);
    }
}
