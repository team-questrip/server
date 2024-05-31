package com.questrip.reward.api.v1.response;

import com.questrip.reward.domain.user.User;

public record UserResponse(Long id,
                           String username,
                           String email,
                           User.Role role) {
    public UserResponse(User user) {
        this(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }
}
