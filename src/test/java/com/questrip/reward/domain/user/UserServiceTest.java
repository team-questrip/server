package com.questrip.reward.domain.user;

import com.questrip.reward.fixture.UserFixture;
import com.questrip.reward.storage.mysql.UserEntity;
import com.questrip.reward.storage.mysql.UserJpaRepository;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserJpaRepository userJpaRepository;

    @AfterEach
    void tearDown() {
        userJpaRepository.deleteAllInBatch();
    }

    @DisplayName("중복 이메일로 가입 시도할 경우 예외가 발생한다.")
    @Test
    void register() {
        // given
        User initUser = UserFixture.get();
        userJpaRepository.save(UserEntity.from(initUser));

        // when & then
        assertThatThrownBy(() -> userService.register(initUser))
                .isInstanceOf(GlobalException.class)
                .hasMessageContaining(ErrorCode.DUPLICATED_EMAIL.getMessage());
    }
}