package com.questrip.reward.domain.user;

import lombok.Getter;

@Getter
public class UserWithToken {
    private User user;
    private String accessToken;
    private String refreshToken;

    public UserWithToken(User user, String accessToken, String refreshToken) {
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
