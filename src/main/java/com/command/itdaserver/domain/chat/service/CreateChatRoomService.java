package com.command.itdaserver.domain.chat.service;

import com.command.itdaserver.domain.chat.domain.ChatRoom;
import com.command.itdaserver.domain.chat.domain.ChatRoomUser;
import com.command.itdaserver.domain.chat.domain.repository.ChatRoomRepository;
import com.command.itdaserver.domain.chat.domain.repository.ChatRoomUserRepository;
import com.command.itdaserver.domain.chat.presentaion.dto.request.ChatRoomUserRequest;
import com.command.itdaserver.domain.chat.presentaion.dto.response.ChatUserResponse;
import com.command.itdaserver.domain.chat.presentaion.dto.response.FirstChatRoomResponse;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomUserRepository chatRoomUserRepository;
    private final UserRepository userRepository;

    private final static int SINGLE_ROOM = 2;

    @Transactional
    public FirstChatRoomResponse execute(ChatRoomUserRequest chatRoomUserRequest, Long myId) {
        List<Long> ids = chatRoomUserRequest.userIds()
                .stream()
                .distinct()
                .collect(Collectors.toList());

        if (!ids.contains(myId)) {
            ids.add(myId);
        }

        List<User> users = userRepository.findAllById(ids);

        if(users.size() != ids.size()) {
            throw UserNotFoundException.EXCEPTION;
        }

        Optional<ChatRoom> existingRoom = chatRoomUserRepository.findChatRoomByUsers(ids, (long) ids.size());

        List<String> userNames = users
                .stream()
                .map(User::getName)
                .collect(Collectors.toList());

        String roomName = createRoomName(userNames);

        List<String> roomImage = users
                .stream()
                .map(User::getImageUrl)
                .limit(2)
                .collect(Collectors.toList());

        List<ChatUserResponse> chatUserResponses = users
                .stream()
                .map(ChatUserResponse::from)
                .collect(Collectors.toList());

        if (chatRoomUserRepository.findChatRoomByUsers(ids, (long) ids.size()).isPresent()) {
            return FirstChatRoomResponse.of(
                    existingRoom.get().getId(),
                    roomName,
                    roomImage,
                    chatUserResponses
            );
        }

        ChatRoom chatRoom = ChatRoom.of(roomName);
        chatRoomRepository.save(chatRoom);

        List<ChatRoomUser> chatRoomUsers = users.stream()
                .map(user -> ChatRoomUser.of(chatRoom, user, null))
                .collect(Collectors.toList());

        chatRoomUserRepository.saveAll(chatRoomUsers);

        return FirstChatRoomResponse.of(
                chatRoom.getId(),
                roomName,
                roomImage,
                chatUserResponses
        );
    }

    private String createRoomName(List<String> userNames){
        if(userNames.size() == SINGLE_ROOM){
            return String.join(", ", userNames);
        }
        else  {
            int otherCount = userNames.size() - SINGLE_ROOM;

            return userNames.get(0) + ", " + userNames.get(1) + " 외 " + otherCount + "명";
        }
    }
}