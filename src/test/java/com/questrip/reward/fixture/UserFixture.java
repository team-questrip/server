package com.questrip.reward.fixture;

import com.questrip.reward.domain.user.User;
import com.questrip.reward.domain.user.UserPreference;
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

    public static UserPreference getPreference(Long userId) {
        return UserPreference.builder()
                .userId(userId)
                .groupSize(1)
                .travelingWithKid(true)
                .vegetarian(false)
                .nonAlcoholic(false)
                .seafoodRestrictions(false)
                .noSpicyFood(false)
                .distance(5)
                .build();
    }
}
