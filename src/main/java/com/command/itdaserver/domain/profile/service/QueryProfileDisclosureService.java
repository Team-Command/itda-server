package com.command.itdaserver.domain.profile.service;

import com.command.itdaserver.domain.profile.exception.UserDisclosureNotFoundException;
import com.command.itdaserver.domain.profile.presentation.dto.response.UserProfileDisclosureResponse;
import com.command.itdaserver.domain.profile.presentation.dto.response.UserPublicProfileResponse;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.UserDisclosure;
import com.command.itdaserver.domain.user.domain.repository.UserDisclosureRepository;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.user.exception.UserNotFoundException;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryProfileDisclosureService {
    private final UserRepository userRepository;
    private final UserDisclosureRepository userDisclosureRepository;

    @Transactional(readOnly = true)
    public UserProfileDisclosureResponse execute(CustomUserDetails customUserDetails) {

        User user = userRepository.findById(customUserDetails.getId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        UserDisclosure userDisclosure = userDisclosureRepository.findByUser(user)
                .orElseThrow(() -> UserDisclosureNotFoundException.EXCEPTION);

        return UserProfileDisclosureResponse.of(userDisclosure);
    }
}
