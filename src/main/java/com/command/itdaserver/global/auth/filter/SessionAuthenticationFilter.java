package com.command.itdaserver.global.auth.filter;

import com.command.itdaserver.domain.auth.domain.UserSession;
import com.command.itdaserver.domain.auth.domain.repository.RememberMeRepository;
import com.command.itdaserver.domain.auth.domain.repository.SessionRepository;
import com.command.itdaserver.domain.user.domain.repository.UserRepository;
import com.command.itdaserver.global.auth.CustomUserDetails;
import com.command.itdaserver.global.util.CookieUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class SessionAuthenticationFilter extends OncePerRequestFilter {

    private final SessionRepository sessionRepository;
    private final RememberMeRepository rememberMeRepository;
    private final UserRepository userRepository;
    private final CookieUtil cookieUtil;

    @Value("${session.expiration:1800}")
    private int sessionExpiration;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        //세션 쿠키 확인
        String sessionId = getCookieValue(request, "SESSION_ID");
        if (sessionId != null) {
            sessionRepository.findById(sessionId).ifPresent(session -> {
                // SecurityContext 설정
                setAuthentication(session);
            });
        }

        // Remember Me 쿠키 확인
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            String rememberMeToken = getCookieValue(request, "REMEMBER_ME");
            if (rememberMeToken != null) {
                rememberMeRepository.findById(rememberMeToken).ifPresent(token -> {
                    // User 정보 조회
                    userRepository.findByUserId(token.getUserId()).ifPresent(user -> {
                        // 새 세션 발급
                        UserSession newSession = UserSession.create(
                                user.getUserId(),
                                user.getEmail(),
                                user.getRole().name(),
                                sessionExpiration
                        );
                        sessionRepository.save(newSession);

                        // 새 세션 쿠키 발급
                        cookieUtil.addSessionCookie(response, newSession.getSessionId());

                        // SecurityContext 설정
                        setAuthentication(newSession);
                    });
                });
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getCookieValue(HttpServletRequest request, String name) {
        if (request.getCookies() == null) return null;
        return Arrays.stream(request.getCookies())
                .filter(c -> name.equals(c.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }

    private void setAuthentication(UserSession session) {
        CustomUserDetails userDetails = new CustomUserDetails(
                session.getUserId(),
                session.getEmail(),
                session.getRole()
        );

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}