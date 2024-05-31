package com.questrip.reward.domain.user;

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

        return userRepository.save(user);
    }
}
