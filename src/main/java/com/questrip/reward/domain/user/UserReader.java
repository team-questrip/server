package com.questrip.reward.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReader {

    private final UserRepository userRepository;

    public User read(String email) {
        return userRepository.findByEmail(email);
    }

    public User readLoginUserEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
