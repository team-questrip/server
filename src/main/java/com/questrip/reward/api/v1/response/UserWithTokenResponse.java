package com.questrip.reward.api.v1.response;

import com.questrip.reward.domain.user.UserWithToken;

public record UserWithTokenResponse(
        UserResponse user,
        String accessToken
) {
    public UserWithTokenResponse(UserWithToken userWithToken) {
        this(new UserResponse(userWithToken.getUser()), userWithToken.getAccessToken());
    }
}
