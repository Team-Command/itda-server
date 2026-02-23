package com.command.itdaserver.domain.user.service;

import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.user.presentation.dto.request.UserPublicProfileRequest;
import com.command.itdaserver.domain.user.presentation.dto.response.UserPublicProfileResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProfileDisclosureService {
    private final UserRepository userRepository;

    public UserPublicProfileResponse execute(UserPublicProfileRequest request) {

    }
}
