package com.command.itdaserver.domain.auth.service;

import com.command.itdaserver.domain.auth.domain.RememberMeToken;
import com.command.itdaserver.domain.auth.domain.UserSession;
import com.command.itdaserver.domain.auth.domain.repository.RememberMeRepository;
import com.command.itdaserver.domain.auth.domain.repository.SessionRepository;
import com.command.itdaserver.domain.auth.presentation.dto.request.LoginRequest;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final SessionRepository sessionRepository;
    private final RememberMeRepository rememberMeRepository;

    @Value("${session.expiration:1800}")
    private int sessionExpiration;

    public LoginResult execute(LoginRequest request) {
        log.info("로그인 시도 - userId: {}", request.userId());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.userId(),
                        request.password()
                )
        );
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        sessionRepository.findByUserPk(userDetails.getId())
                .ifPresent(sessionRepository::delete);

        UserSession session = UserSession.create(
                userDetails.getId(),
                userDetails.getUserId(),
                userDetails.getEmail(),
                userDetails.getRole(),
                sessionExpiration
        );

        sessionRepository.save(session);

        log.info("로그인 성공 - userId: {}, sessionId: {}",
                userDetails.getUserId(), session.getSessionId());

        String rememberMeToken = null;
        if (request.rememberMe()) {
            // 기존 Remember Me 토큰 삭제
            rememberMeRepository.findByUserPk(userDetails.getId())
                    .ifPresent(rememberMeRepository::delete);

            // 새 Remember Me 토큰 생성
            RememberMeToken rememberMe = RememberMeToken.create(
                    userDetails.getUserId()
            );
            rememberMeRepository.save(rememberMe);
            rememberMeToken = rememberMe.getToken();

            log.info("Remember Me 토큰 생성 - userId: {}", userDetails.getUserId());
        }
        return new LoginResult(session.getSessionId(), rememberMeToken);
    }
}
