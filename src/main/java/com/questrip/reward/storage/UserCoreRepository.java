package com.questrip.reward.storage;

import com.questrip.reward.domain.user.User;
import com.questrip.reward.domain.user.UserRepository;
import com.questrip.reward.storage.mysql.UserEntity;
import com.questrip.reward.storage.mysql.UserJpaRepository;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserCoreRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;

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
            throw new GlobalException(ErrorCode.DUPLICATED_EMAIL, String.format("email is duplicated. request emila is %s", email));
        }
    }
}
