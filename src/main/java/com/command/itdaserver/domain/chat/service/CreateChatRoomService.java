package com.command.itdaserver.domain.chat.service;

import com.command.itdaserver.domain.chat.domain.ChatRoom;
import com.command.itdaserver.domain.chat.domain.ChatRoomUser;
import com.command.itdaserver.domain.chat.domain.repository.ChatRoomRepository;
import com.command.itdaserver.domain.chat.domain.repository.ChatRoomUserRepository;
import com.command.itdaserver.domain.chat.presentaion.dto.request.ChatRoomUserRequest;
import com.command.itdaserver.domain.chat.presentaion.dto.response.FirstChatRoomResponse;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomUserRepository chatRoomUserRepository;
    private final UserRepository userRepository;

    private final static int SINGLE_ROOM = 1;

    @Transactional
    public FirstChatRoomResponse firstCreate(ChatRoomUserRequest chatRoomUserRequest) {
        List<Long> ids = chatRoomUserRequest.id();

        List<User> users = userRepository.findAllById(ids);

        List<String> userNames = users
                .stream()
                .map(User::getName)
                .collect(Collectors.toList());

        String roomName = createRoomName(userNames);

        ChatRoom chatRoom = ChatRoom.of(roomName);

        List<ChatRoomUser> chatRoomUsers = users
                .stream()
                .map(user -> ChatRoomUser.of(
                        chatRoom,
                        user,

                ))
                .collect(Collectors.toList());
    }

    private String createRoomName(List<String> userNames){
        if(userNames.size() == SINGLE_ROOM){
            return String.join(", ", userNames);
        }
        else  {
            int otherCount = userNames.size() - SINGLE_ROOM;

            return userNames.get(0) + userNames.get(1) + "외 " + otherCount + "명";
        }
    }
}