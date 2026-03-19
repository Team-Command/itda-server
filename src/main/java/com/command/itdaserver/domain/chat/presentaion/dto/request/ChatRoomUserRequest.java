package com.command.itdaserver.domain.chat.presentaion.dto.request;

import java.util.List;

public record ChatRoomUserRequest(
        List<Long> id
) {
}
