package com.command.itdaserver.domain.profile.service;

import com.command.itdaserver.domain.profile.exception.UserDisclosureNotFoundException;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.UserDisclosure;
import com.command.itdaserver.domain.user.domain.repository.UserDisclosureRepository;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.user.exception.UserNotFoundException;
import com.command.itdaserver.domain.profile.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryMyProfileService {
    private final UserRepository userRepository;
    private final UserDisclosureRepository userDisclosureRepository;

    @Transactional(readOnly = true)
    public UserResponse execute(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        UserDisclosure userDisclosure = userDisclosureRepository.findByUser(user)
                .orElseThrow(() -> UserDisclosureNotFoundException.EXCEPTION);

        return UserResponse.from(user);
    }
}
