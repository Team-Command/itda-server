package com.command.itdaserver.domain.profile.service;

import com.command.itdaserver.domain.user.domain.repository.UserDisclosureRepository;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.profile.presentation.dto.request.UserPublicProfileRequest;
import com.command.itdaserver.domain.profile.presentation.dto.response.UserPublicProfileResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProfileDisclosureService {
    private final UserRepository userRepository;
    private final UserDisclosureRepository userDisclosureRepository;

    public UserPublicProfileResponse execute(UserPublicProfileRequest request) {

    }
}
