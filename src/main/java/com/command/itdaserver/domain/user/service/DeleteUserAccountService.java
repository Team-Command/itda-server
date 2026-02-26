package com.command.itdaserver.domain.user.service;

import com.command.itdaserver.domain.auth.domain.repository.SessionRepository;
import com.command.itdaserver.domain.user.domain.User;
import com.command.itdaserver.domain.user.domain.UserDisclosure;
import com.command.itdaserver.domain.user.domain.repository.UserDisclosureRepository;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.domain.user.exception.UserNotFoundException;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteUserAccountService {
    private final UserRepository userRepository;
    private final UserDisclosureRepository userDisclosureRepository;
    private final SessionRepository sessionRepository;

    @Transactional
    public void execute(CustomUserDetails customUserDetails) {

        userRepository.findById(customUserDetails.getId()).ifPresent(deleteUser -> {
            userDisclosureRepository.findById(deleteUser.getId())
                    .ifPresent(userDisclosureRepository::delete);
            sessionRepository.findByUserPk(deleteUser.getId())
                    .ifPresent(sessionRepository::delete);
        });

        SecurityContextHolder.clearContext();
    }
}
