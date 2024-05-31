package com.questrip.reward.domain.user;

import lombok.Getter;

@Getter
public class UserWithToken {
    private User user;
    private String accessToken;

    public UserWithToken(User user, String accessToken) {
        this.user = user;
        this.accessToken = accessToken;
    }
}
