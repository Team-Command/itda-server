package com.command.itdaserver.domain.user.service;

import com.command.itdaserver.domain.auth.domain.repository.SessionRepository;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteUserAccountService {
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    @Transactional
    public void execute(CustomUserDetails customUserDetails) {
        String deleteUserId = customUserDetails.getUserId();

        userRepository.findByUserId(deleteUserId).ifPresent(user -> {
            sessionRepository.deleteByUserId(deleteUserId);
            userRepository.delete(user);
        });

        SecurityContextHolder.clearContext();
    }
}
