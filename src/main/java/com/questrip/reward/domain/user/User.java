package com.questrip.reward.domain.user;

import com.questrip.reward.security.jwt.JwtUtils;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private String refreshToken;

    public enum Role {
        USER
    }

    public void encodePassword(String password) {
        this.password = password;
    }

    public void issueRefreshToken() {
        this.refreshToken = JwtUtils.generateRefreshToken(this);
    }

    @Builder
    private User(Long id, String username, String email, String password, Role role, String refreshToken) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.refreshToken = refreshToken;
    }
}
