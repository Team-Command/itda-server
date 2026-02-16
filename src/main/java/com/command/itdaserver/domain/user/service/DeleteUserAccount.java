package com.command.itdaserver.domain.user.service;

import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserAccount {
    private final UserRepository userRepository;

    public void execute(CustomUserDetails customUserDetails) {
        String deleteUserId = customUserDetails.getUserId();

        userRepository.deleteByUserId(deleteUserId);
    }
}
