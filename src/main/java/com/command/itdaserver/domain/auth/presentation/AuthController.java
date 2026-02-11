package com.command.itdaserver.domain.auth.presentation;

import com.command.itdaserver.domain.auth.presentation.dto.request.LoginRequest;
import com.command.itdaserver.domain.auth.presentation.dto.request.SignUpRequest;
import com.command.itdaserver.domain.auth.presentation.dto.response.LoginResponse;
import com.command.itdaserver.domain.auth.presentation.dto.response.SignUpResponse;
import com.command.itdaserver.domain.auth.service.LoginService;
import com.command.itdaserver.domain.auth.service.SignUpService;
import com.command.itdaserver.domain.auth.service.command.SignUpCommand;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignUpService signUpService;
    private final LoginService loginService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest request){
        SignUpCommand command = SignUpCommand.from(request);
        signUpService.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SignUpResponse("회원가입이 되었습니다, 로그인 후 서비스를 이용해주세요."));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request,
            HttpServletResponse response
    ){
        String sessionId = loginService.execute(request);

        addSessionCookie(response, sessionId);

        return ResponseEntity.ok(new LoginResponse("로그인 성공"));
    }

    private void addSessionCookie(HttpServletResponse response, String sessionId) {
        Cookie cookie = new Cookie("SESSION_ID", sessionId);
        cookie.setHttpOnly(true);      // XSS 방어
        cookie.setSecure(true);        // HTTPS만 전송
        cookie.setPath("/");           // 모든 경로에서 전송
        cookie.setMaxAge(1800);        // 30분


        response.addCookie(cookie);
    }

}
