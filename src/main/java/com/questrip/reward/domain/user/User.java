package com.questrip.reward.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;


    public enum Role {
        USER
    }

    public void encodePassword(String password) {
        this.password = password;
    }

    @Builder
    private User(Long id, String username, String email, String password, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
