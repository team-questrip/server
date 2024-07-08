package com.questrip.reward.domain.user;

import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLoginProcessor {

    private final UserReader userReader;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User login(String email, String password) {
        User user = userReader.readLoginUserEmail(email);
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new GlobalException(ErrorCode.INVALID_PASSWORD);
        }
        user.issueRefreshToken();
        userRepository.updateRefreshToken(user);

        return user;
    }
}
