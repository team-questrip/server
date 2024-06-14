package com.questrip.reward.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;

    public void validateEmail(String email) {
        userRepository.validateDuplicatedEmail(email);
    }
}
