package com.command.itdaserver.domain.user.service;

import com.command.itdaserver.domain.auth.domain.repository.SessionRepository;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserAccount {
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    public void execute(CustomUserDetails customUserDetails) {
        String deleteUserId = customUserDetails.getUserId();

        userRepository.deleteByUserId(deleteUserId);
        sessionRepository.deleteByUserId(deleteUserId);
    }
}
