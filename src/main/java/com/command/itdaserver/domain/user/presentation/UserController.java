package com.command.itdaserver.domain.user.presentation;

import com.command.itdaserver.domain.auth.domain.enums.CookieNames;
import com.command.itdaserver.domain.profile.presentation.dto.response.UserPublicProfileResponse;
import com.command.itdaserver.domain.user.presentation.dto.response.DeleteUserResponse;
import com.command.itdaserver.domain.user.presentation.dto.response.UserResponse;
import com.command.itdaserver.domain.user.service.DeleteUserAccountService;
import com.command.itdaserver.domain.user.service.QueryMyProfileService;
import com.command.itdaserver.global.auth.CustomUserDetails;
import com.command.itdaserver.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final CookieUtil cookieUtil;

    private final DeleteUserAccountService deleteUserAccount;
    private final QueryMyProfileService queryMyProfileService;

    @DeleteMapping
    public ResponseEntity<DeleteUserResponse> deleteUserAccount(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            HttpServletResponse response
    ) {
        deleteUserAccount.execute(customUserDetails);

        cookieUtil.removeCookie(response, CookieNames.SESSION_ID.getName());
        cookieUtil.removeCookie(response, CookieNames.REMEMBER_ME.getName());

        return ResponseEntity.ok(new DeleteUserResponse("회원 탈퇴가 완료되었습니다"));
    }

    @GetMapping("/me")
    public UserResponse getMyProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return queryMyProfileService.execute(customUserDetails.getUserId());
    }
}
