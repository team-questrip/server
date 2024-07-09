package com.questrip.reward.storage;

import com.questrip.reward.domain.user.User;
import com.questrip.reward.domain.user.UserPreference;
import com.questrip.reward.domain.user.UserRepository;
import com.questrip.reward.storage.mysql.UserEntity;
import com.questrip.reward.storage.mysql.UserJpaRepository;
import com.questrip.reward.storage.mysql.UserPreferenceEntity;
import com.questrip.reward.storage.mysql.UserPreferenceJpaRepository;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserCoreRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserPreferenceJpaRepository userPreferenceJpaRepository;

    @Override
    public User save(User user) {
        return userJpaRepository.save(UserEntity.from(user)).toUser();
    }

    @Override
    public User findByEmail(String email) {
        return userJpaRepository.findByEmail(email).orElseThrow(
                () -> new GlobalException(ErrorCode.USER_NOT_FOUND, String.format("user not found. user email is %s", email))
        ).toUser();
    }

    @Override
    public void validateDuplicatedEmail(String email) {
        if(userJpaRepository.existsByEmail(email)) {
            throw new GlobalException(ErrorCode.DUPLICATED_EMAIL, String.format("email is duplicated. request email is %s", email));
        }
    }

    @Override
    public User findUserByEmail(String email) {
        return userJpaRepository.findByEmail(email).orElseThrow(
                () -> new GlobalException(ErrorCode.USERNAME_NOT_FOUND, String.format("user not found. user email is %s", email))
        ).toUser();
    }

    @Transactional
    @Override
    public void updateRefreshToken(User user) {
        userJpaRepository.findById(user.getId())
                .orElseThrow()
                .updateRefreshToken(user.getRefreshToken());
    }

    @Override
    public UserPreference findUserPreference(Long userId) {
        return userPreferenceJpaRepository.findTopByUserIdOrderByIdDesc(userId)
                .map(UserPreferenceEntity::toUserPreference)
                .orElse(UserPreference.defaultPreference(userId));
    }

    @Override
    public UserPreference savePreference(UserPreference userPreference) {
        return userPreferenceJpaRepository.save(UserPreferenceEntity.from(userPreference))
                .toUserPreference();
    }
}
