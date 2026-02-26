package com.command.itdaserver.domain.auth.presentation;

import com.command.itdaserver.domain.auth.domain.enums.CookieNames;
import com.command.itdaserver.domain.auth.presentation.dto.request.ChangePasswordRequest;
import com.command.itdaserver.domain.auth.presentation.dto.request.LoginRequest;
import com.command.itdaserver.domain.auth.presentation.dto.request.SignUpRequest;
import com.command.itdaserver.domain.auth.presentation.dto.request.VerifyEmailCodeRequest;
import com.command.itdaserver.domain.auth.presentation.dto.response.LoginResponse;
import com.command.itdaserver.domain.auth.presentation.dto.response.LogoutResponse;
import com.command.itdaserver.domain.auth.presentation.dto.response.SignUpResponse;
import com.command.itdaserver.domain.auth.presentation.dto.response.VerifyEmailCodeResponse;
import com.command.itdaserver.domain.auth.service.*;
import com.command.itdaserver.global.auth.CustomUserDetails;
import com.command.itdaserver.global.common.response.MessageResponse;
import com.command.itdaserver.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final CookieUtil cookieUtil;

    private final SignUpService signUpService;
    private final LoginService loginService;
    private final LogoutService logoutService;
    private final PasswordResetService passwordResetService;
    private final VerifyEmailCodeService verifyEmailCodeService;
    private final ChangePasswordService changePasswordService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest request){
        signUpService.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SignUpResponse("회원가입이 되었습니다, 로그인 후 서비스를 이용해주세요."));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request,
            HttpServletResponse response
    ){
        LoginResult result = loginService.execute(request);

        cookieUtil.addSessionCookie(response, result.sessionId());

        if (result.rememberMeToken() != null) {
            cookieUtil.addRememberMeCookie(response, result.rememberMeToken());
        }

        return ResponseEntity.ok(new LoginResponse("로그인 성공"));
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            HttpServletResponse response){
        logoutService.execute(customUserDetails);

        cookieUtil.removeCookie(response, CookieNames.SESSION_ID.getName());
        cookieUtil.removeCookie(response, CookieNames.REMEMBER_ME.getName());

        return ResponseEntity.ok(new LogoutResponse("로그아웃에 성공했습니다."));
    }

    @PostMapping("password/verification")
    public ResponseEntity<MessageResponse> sendVerificationEmail(
            @AuthenticationPrincipal CustomUserDetails userDetails
            ){
        passwordResetService.execute(userDetails.getEmail());
        return ResponseEntity.ok(MessageResponse.of("해당 이메일로 인증번호가 발송되었습니다, 인증코드를 입력해주세요."));
    }

    @PostMapping("password/verification/confirm")
    public ResponseEntity<VerifyEmailCodeResponse> verifyEmailCode(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody VerifyEmailCodeRequest request
            ){
        verifyEmailCodeService.validateEmail(userDetails.getEmail(), request.email());
        String resetToken = verifyEmailCodeService.execute(request.email(), request.code());
        return ResponseEntity.ok(new VerifyEmailCodeResponse(resetToken));
    }

    @PatchMapping("password")
    public ResponseEntity<MessageResponse> changePassword(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody ChangePasswordRequest request
            ){
        changePasswordService.execute(userDetails.getEmail(), userDetails.getId(), request);

        return ResponseEntity.ok(MessageResponse.of("비밀번호가 변경되었습니다."));
    }
}
