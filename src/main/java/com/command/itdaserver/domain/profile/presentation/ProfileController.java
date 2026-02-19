package com.command.itdaserver.domain.profile.presentation;

import com.command.itdaserver.domain.profile.service.QueryUserProfileService;
import com.command.itdaserver.domain.user.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final QueryUserProfileService queryUserProfileService;

    @GetMapping("/{userId}")
    public UserResponse queryUserProfile(@PathVariable String userId) {
        return queryUserProfileService.execute(userId);
    }
}
