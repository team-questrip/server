package com.questrip.reward.storage.mysql;

import com.questrip.reward.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private User.Role role;
    private String refreshToken;

    public User toUser() {
        return User.builder()
                .id(id)
                .username(username)
                .email(email)
                .password(password)
                .role(role)
                .refreshToken(refreshToken)
                .build();
    }

    public static UserEntity from(User user) {
        return UserEntity.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .refreshToken(user.getRefreshToken())
                .build();
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Builder
    private UserEntity(String username, String email, String password, User.Role role, String refreshToken) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.refreshToken = refreshToken;
    }
}
