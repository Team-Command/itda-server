package com.command.itdaserver.domain.profile.presentation.dto.response;

public record MyProfileResponse(
        UserResponse userResponse,
        UserProfileDisclosureResponse MyPublicProfileResponse
) {
    public static MyProfileResponse of(
            UserResponse userResponse,
            UserProfileDisclosureResponse MyPublicProfileResponse
    ) {
        return new MyProfileResponse(userResponse, MyPublicProfileResponse);
    }
}
