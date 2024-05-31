package com.questrip.reward.api.v1.request;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginRequest(
        @NotEmpty(message = "이메일은 필수입니다.")
        String email,
        @NotEmpty(message = "비밀번호는 필수입니다.")
        String password) {
}
