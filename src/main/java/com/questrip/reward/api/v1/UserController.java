package com.questrip.reward.api.v1;

import com.questrip.reward.api.v1.request.UserCreateRequest;
import com.questrip.reward.api.v1.request.UserEmailValidateRequest;
import com.questrip.reward.api.v1.request.UserLoginRequest;
import com.questrip.reward.api.v1.response.UserWithTokenResponse;
import com.questrip.reward.domain.user.UserService;
import com.questrip.reward.domain.user.UserWithToken;
import com.questrip.reward.support.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ApiResponse<UserWithTokenResponse> register(@Valid @RequestBody UserCreateRequest request) {
        UserWithToken user = userService.register(request.toUser());

        return ApiResponse.success("회원가입 완료", new UserWithTokenResponse(user));
    }

    @PostMapping("/login")
    public ApiResponse<UserWithTokenResponse> login(@Valid @RequestBody UserLoginRequest request) {
        UserWithToken user = userService.login(request.email(), request.password());

        return ApiResponse.success("로그인 완료", new UserWithTokenResponse(user));
    }

    @PostMapping("/checkEmail")
    public ApiResponse<Void> checkEmail(@RequestBody UserEmailValidateRequest request) {
        userService.validateDuplicatedEmail(request.email());

        return ApiResponse.success("이메일 사용 가능");
    }

    @PostMapping("/reissue")
    public ApiResponse<UserWithTokenResponse> reIssue(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserWithToken userWithToken = userService.reIssue(token);

        return ApiResponse.success(new UserWithTokenResponse(userWithToken));
    }
}
