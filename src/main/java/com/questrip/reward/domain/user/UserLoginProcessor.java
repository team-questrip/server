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

    public User login(String email, String password) {
        User user = userReader.read(email);
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new GlobalException(ErrorCode.INVALID_PASSWORD);
        }

        return user;
    }
}
