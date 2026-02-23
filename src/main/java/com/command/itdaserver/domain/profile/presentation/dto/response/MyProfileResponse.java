package com.command.itdaserver.domain.profile.presentation.dto.response;

public record MyProfileResponse(
        UserResponse userResponse,
        MyPublicProfileResponse MyPublicProfileResponse
) {
    public static MyProfileResponse of(
            UserResponse userResponse,
            MyPublicProfileResponse MyPublicProfileResponse
    ) {
        return new MyProfileResponse(userResponse, MyPublicProfileResponse);
    }
}
