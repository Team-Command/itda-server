package com.command.itdaserver.domain.profile.service;

import com.command.itdaserver.domain.profile.exception.UserIdDuplicateException;
import com.command.itdaserver.domain.profile.presentation.dto.request.UserProfileRequest;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.user.exception.UserNotFoundException;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateMyProfileService {
    private final UserRepository userRepository;

    public void execute(UserProfileRequest request, CustomUserDetails customUserDetails) {
        userRepository.findByUserId(request.userId())
                .orElseThrow(() -> UserIdDuplicateException.EXCEPTION);

        User user = userRepository.findByUserId(customUserDetails.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        user.

    }
}
