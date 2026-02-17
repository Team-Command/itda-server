package com.command.itdaserver.domain.user.service;

import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.user.exception.UserNotFoundException;
import com.command.itdaserver.domain.user.presentation.dto.response.UserResponse;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryMyProfileService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserResponse execute(CustomUserDetails customUserDetails) {
        User user = userRepository.findByUserId(customUserDetails.getUserId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return UserResponse.from(user);
    }
}
