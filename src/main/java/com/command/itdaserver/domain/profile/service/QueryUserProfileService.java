package com.command.itdaserver.domain.profile.service;

import com.command.itdaserver.domain.profile.exception.UserDisclosureNotFoundException;
import com.command.itdaserver.domain.profile.presentation.dto.response.UserPublicProfileResponse;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.UserDisclosure;
import com.command.itdaserver.domain.user.domain.repository.UserDisclosureRepository;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryUserProfileService {
    private final UserRepository userRepository;
    private final UserDisclosureRepository userDisclosureRepository;

    public UserPublicProfileResponse execute(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        UserDisclosure userDisclosure = userDisclosureRepository.findByUser(user)
                .orElseThrow(() -> UserDisclosureNotFoundException.EXCEPTION);

        return UserPublicProfileResponse.from(user, userDisclosure);
    }
}
