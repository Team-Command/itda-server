package com.command.itdaserver.domain.chat.presentaion;

import com.command.itdaserver.domain.chat.presentaion.dto.request.ChatRoomUserRequest;
import com.command.itdaserver.domain.chat.presentaion.dto.response.FirstChatRoomResponse;
import com.command.itdaserver.domain.chat.service.CreateChatRoomService;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final CreateChatRoomService createChatRoomService;

    @PostMapping()
    public FirstChatRoomResponse createRoom(
            @RequestBody ChatRoomUserRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        return createChatRoomService.execute(request, customUserDetails.getId());
    }
}
