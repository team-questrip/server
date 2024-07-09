package com.questrip.reward.domain.user;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User save(User user);

    User findByEmail(String email);

    void validateDuplicatedEmail(String email);

    User findUserByEmail(String email);

    void updateRefreshToken(User user);

    UserPreference findUserPreference(Long userId);

    UserPreference savePreference(UserPreference userPreference);
}
