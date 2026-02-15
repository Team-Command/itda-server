package com.command.itdaserver.domain.auth.service;

import com.command.itdaserver.domain.auth.domain.repository.RememberMeRepository;
import com.command.itdaserver.domain.auth.domain.repository.SessionRepository;
import com.command.itdaserver.domain.auth.presentation.dto.response.LogoutResponse;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService {
    private final SessionRepository sessionRepository;
    private final RememberMeRepository rememberMeRepository;

    public void execute(CustomUserDetails customUserDetails) {
        sessionRepository.findByUserId(customUserDetails.getUserId())
                .ifPresent(session -> {
                    sessionRepository.delete(session);
                    rememberMeRepository.deleteByUserId(customUserDetails.getUserId());
                });
    }
}
