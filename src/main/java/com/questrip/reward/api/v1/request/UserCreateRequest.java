package com.questrip.reward.api.v1.request;

import com.questrip.reward.domain.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record UserCreateRequest(
        String username,
        @NotEmpty(message = "이메일은 필수입니다.")
        @Email(message = "유효하지 않은 이메일 형식입니다.",
                regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
        String email,
        @NotEmpty(message = "비밀번호는 필수입니다.")
        @Pattern(regexp = "^[a-z0-9]{8,20}$", message = "비밀번호 형식에 맞지 않습니다. 문자, 숫자가 포함된 8~20자리로 입력해주세요.")
        String password) {

    public User toUser() {
        return User.builder()
                .username(username)
                .email(email)
                .password(password)
                .role(User.Role.USER)
                .build();
    }
}
