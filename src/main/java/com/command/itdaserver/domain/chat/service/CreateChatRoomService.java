package com.command.itdaserver.domain.chat.service;

import com.command.itdaserver.domain.chat.domain.repository.ChatRoomRepository;
import com.command.itdaserver.domain.chat.presentaion.dto.request.ChatRoomUserRequest;
import com.command.itdaserver.domain.chat.presentaion.dto.response.FirstChatRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public FirstChatRoomResponse firstCreate(ChatRoomUserRequest chatRoomUserRequest) {
        List<Long> ids = chatRoomUserRequest.id();


    }
}