package com.questrip.reward.domain.user;

import com.questrip.reward.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAppender {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User append(User user) {
        user.encodePassword(passwordEncoder.encode(user.getPassword()));
        user.issueRefreshToken();

        return userRepository.save(user);
    }
}
