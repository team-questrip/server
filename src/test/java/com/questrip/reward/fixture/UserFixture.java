package com.questrip.reward.fixture;

import com.questrip.reward.domain.user.User;
import com.questrip.reward.domain.user.UserWithToken;

public class UserFixture {

    public static User get() {
        return User.builder()
                .username("harok")
                .email("harok@questrips.com")
                .password("harok123")
                .role(User.Role.USER)
                .refreshToken("refreshToken")
                .build();
    }

    public static User get(Long id) {
        return User.builder()
                .id(id)
                .username("harok")
                .email("harok@questrips.com")
                .password("harok123")
                .role(User.Role.USER)
                .refreshToken("refreshToken")
                .build();
    }

    public static UserWithToken getUserWithToken() {
        return new UserWithToken(get(1L), "accessToken", "refreshToken");
    }
}
