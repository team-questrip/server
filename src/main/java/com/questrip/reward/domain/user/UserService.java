package com.questrip.reward.domain.user;

import com.questrip.reward.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserAppender userAppender;
    private final UserLoginProcessor userLoginProcessor;
    private final UserValidator userValidator;

    public UserWithToken register(User initUser) {
        validateDuplicatedEmail(initUser.getEmail());
        User user = userAppender.append(initUser);
        String token = JwtUtils.generateAccessToken(user);

        return new UserWithToken(user, token);
    }

    public UserWithToken login(String email, String password) {
        User user = userLoginProcessor.login(email, password);
        String token = JwtUtils.generateAccessToken(user);

        return new UserWithToken(user, token);
    }

    public void validateDuplicatedEmail(String email) {
        userValidator.validateEmail(email);
    }
}
