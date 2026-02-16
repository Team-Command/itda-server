package com.command.itdaserver.domain.user.presentation;

import com.command.itdaserver.domain.user.presentation.dto.response.DeleteUserResponse;
import com.command.itdaserver.domain.user.service.DeleteUserAccountService;
import com.command.itdaserver.global.auth.CustomUserDetails;
import com.command.itdaserver.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final CookieUtil cookieUtil;

    private final DeleteUserAccountService deleteUserAccount;

    private static final String sessionId = "SESSION_ID";
    private static final String rememberMe = "REMEMBER_ME";

    @DeleteMapping
    public ResponseEntity<DeleteUserResponse> deleteUserAccount(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            HttpServletResponse response
    ) {
        deleteUserAccount.execute(customUserDetails);

        cookieUtil.removeCookie(response, sessionId);
        cookieUtil.removeCookie(response, rememberMe);

        return ResponseEntity.ok(new DeleteUserResponse("회원 탈퇴가 완료되었습니다"));
    }
}
