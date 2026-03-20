package com.command.itdaserver.domain.chat.service;

import com.command.itdaserver.domain.chat.domain.repository.ChatRoomRepository;
import com.command.itdaserver.domain.chat.domain.repository.ChatRoomUserRepository;
import com.command.itdaserver.domain.chat.presentaion.dto.request.ChatRoomUserRequest;
import com.command.itdaserver.domain.chat.presentaion.dto.response.FirstChatRoomResponse;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomUserRepository chatRoomUserRepository;
    private final UserRepository userRepository;

    private final static String SINGLE_ROOM;

    public FirstChatRoomResponse firstCreate(ChatRoomUserRequest chatRoomUserRequest) {
        List<Long> ids = chatRoomUserRequest.id();

        List<User> users = userRepository.findAllById(ids);

        if(users.size() > 2){

        }

    }
}