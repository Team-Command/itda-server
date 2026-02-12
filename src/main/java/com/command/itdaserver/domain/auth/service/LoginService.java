package com.command.itdaserver.domain.auth.service;

import com.command.itdaserver.domain.auth.domain.UserSession;
import com.command.itdaserver.domain.auth.domain.repository.SessionRepository;
import com.command.itdaserver.domain.auth.presentation.dto.request.LoginRequest;
import com.command.itdaserver.domain.auth.presentation.dto.response.LoginResponse;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final SessionRepository sessionRepository;

    @Value("${session.expiration:1800}")
    private Long sessionExpiration;

    public String execute(LoginRequest request){
        log.info("로그인 시도 - userId: {}", request.userId());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.userId(),
                        request.password()
                )
        );
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        sessionRepository.deleteByUserId(userDetails.getUserId());

        UserSession session = UserSession.create(
                userDetails.getUserId(),
                userDetails.getEmail(),
                userDetails.getRole(),
                sessionExpiration
        );

        sessionRepository.save(session);
        log.info("로그인 성공 - userId: {}, sessionId: {}",
                userDetails.getUserId(), session.getSessionId());

        return session.getSessionId();
    }

}
