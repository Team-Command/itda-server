package com.command.itdaserver.domain.profile.service;

import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.repository.UserDisclosureRepository;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.profile.presentation.dto.request.UserPublicProfileRequest;
import com.command.itdaserver.domain.profile.presentation.dto.response.UserPublicProfileResponse;
import com.command.itdaserver.domain.user.exception.UserNotFoundException;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProfileDisclosureService {
    private final UserRepository userRepository;
    private final UserDisclosureRepository userDisclosureRepository;

    public UserPublicProfileResponse execute(
            UserPublicProfileRequest request,
            CustomUserDetails customUserDetails
    ) {
        String userId = customUserDetails.getUserId();

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);


    }
}
