package com.questrip.reward.domain.user;

import com.questrip.reward.security.jwt.JwtUtils;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserAppender userAppender;
    private final UserLoginProcessor userLoginProcessor;
    private final UserValidator userValidator;
    private final UserReader userReader;

    public UserWithToken register(User initUser) {
        // TODO : 선호도
        validateDuplicatedEmail(initUser.getEmail());
        User user = userAppender.append(initUser);
        String accessToken = JwtUtils.generateAccessToken(user);

        return new UserWithToken(user, accessToken, user.getRefreshToken());
    }

    public UserWithToken login(String email, String password) {
        User user = userLoginProcessor.login(email, password);
        String accessToken = JwtUtils.generateAccessToken(user);

        return new UserWithToken(user, accessToken, user.getRefreshToken());
    }

    public void validateDuplicatedEmail(String email) {
        userValidator.validateEmail(email);
    }

    public UserWithToken reIssue(String refreshToken) {
        if(refreshToken == null) {
            throw new GlobalException(ErrorCode.NOT_FOUND_TOKEN);
        }

        String emailFromToken = JwtUtils.getEmailFromToken(refreshToken);
        User user = userReader.read(emailFromToken);
        if (!user.getRefreshToken().equals(refreshToken)) {
            throw new GlobalException(ErrorCode.INVALID_JWT_TOKEN);
        }
        String newToken = JwtUtils.generateAccessToken(user);

        return new UserWithToken(user, newToken, user.getRefreshToken());
    }

    public UserPreference getPreference(Long userId) {
        return userReader.readPreference(userId);
    }

    public UserPreference savePreference(UserPreference userPreference) {
        return userAppender.appendPreference(userPreference);
    }
}
