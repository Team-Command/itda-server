package com.command.itdaserver.domain.user.presentation;

import com.command.itdaserver.domain.auth.presentation.dto.response.LogoutResponse;
import com.command.itdaserver.domain.user.service.DeleteUserAccount;
import com.command.itdaserver.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final DeleteUserAccount deleteUserAccount;

    @DeleteMapping
    public ResponseEntity<String> deleteUserAccount(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        deleteUserAccount.execute(customUserDetails);

        return ResponseEntity.ok(new LogoutResponse("로그아웃에 성공했습니다."));
    }

}
