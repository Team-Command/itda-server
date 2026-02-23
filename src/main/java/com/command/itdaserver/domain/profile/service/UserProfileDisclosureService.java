package com.command.itdaserver.domain.profile.service;

import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.UserDisclosure;
import com.command.itdaserver.domain.user.domain.repository.UserDisclosureRepository;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.profile.presentation.dto.request.UserPublicProfileRequest;
import com.command.itdaserver.domain.user.exception.UserNotFoundException;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileDisclosureService {
    private final UserRepository userRepository;
    private final UserDisclosureRepository userDisclosureRepository;

    @Transactional
    public void execute(
            UserPublicProfileRequest request,
            CustomUserDetails customUserDetails
    ) {
        String userId = customUserDetails.getUserId();

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        UserDisclosure userDisclosure = userDisclosureRepository.findByUser(user)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        userDisclosure.update(request);

        userDisclosureRepository.save(userDisclosure);
    }
}
