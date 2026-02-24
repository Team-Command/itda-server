package com.command.itdaserver.domain.profile.presentation;

import com.command.itdaserver.domain.profile.presentation.dto.request.UserPublicProfileRequest;
import com.command.itdaserver.domain.profile.presentation.dto.response.MyProfileResponse;
import com.command.itdaserver.domain.profile.presentation.dto.response.UserPublicProfileResponse;
import com.command.itdaserver.domain.profile.service.QueryMyProfileService;
import com.command.itdaserver.domain.profile.service.QueryUserProfileService;
import com.command.itdaserver.domain.profile.service.UserProfileDisclosureService;
import com.command.itdaserver.global.auth.CustomUserDetails;
import com.command.itdaserver.global.common.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final QueryMyProfileService queryMyProfileService;
    private final QueryUserProfileService queryUserProfileService;
    private final UserProfileDisclosureService userProfileDisclosureService;

    @GetMapping("/{userId}")
    public UserPublicProfileResponse queryUserProfile(@PathVariable String userId) {
        return queryUserProfileService.execute(userId);
    }

    @PatchMapping("/visability")
    public ResponseEntity<MessageResponse> updateUserProfileDisclosure(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody UserPublicProfileRequest request
    ){
        userProfileDisclosureService.execute(request, customUserDetails);

        return ResponseEntity.ok(MessageResponse.of("프로필 정보가 변경되었습니다."));
    }

    @GetMapping("/me")
    public MyProfileResponse getMyProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return queryMyProfileService.execute(customUserDetails.getUserId());
    }
}
