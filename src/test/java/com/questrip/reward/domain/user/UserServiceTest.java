package com.questrip.reward.domain.user;

import com.questrip.reward.fixture.UserFixture;
import com.questrip.reward.storage.mysql.UserEntity;
import com.questrip.reward.storage.mysql.UserJpaRepository;
import com.questrip.reward.storage.mysql.UserPreferenceJpaRepository;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserJpaRepository userJpaRepository;

    @Autowired
    UserPreferenceJpaRepository userPreferenceJpaRepository;

    @AfterEach
    void tearDown() {
        userJpaRepository.deleteAllInBatch();
        userPreferenceJpaRepository.deleteAllInBatch();
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

    @DisplayName("유저 선호도 중 가장 최신의 선호도를 가져온다.")
    @Test
    void getPreference() {
        // given
        UserEntity user = userJpaRepository.save(UserEntity.from(UserFixture.get()));
        userService.savePreference(UserPreference.defaultPreference(user.getId()));
        UserPreference userPreference2 = userService.savePreference(UserFixture.getPreference(user.getId()));

        // when
        UserPreference preference = userService.getPreference(user.getId());

        // then
        assertThat(preference).isEqualTo(userPreference2);
    }

    @DisplayName("유저 선호도를 저장한다.")
    @Test
    void savePreference() {
        // given
        UserEntity user = userJpaRepository.save(UserEntity.from(UserFixture.get()));

        // when
        UserPreference save = userService.savePreference(UserPreference.defaultPreference(user.getId()));

        // then
        UserPreference find = userPreferenceJpaRepository.findTopByUserIdOrderByIdDesc(user.getId()).get().toUserPreference();
        assertThat(save).isEqualTo(find);
    }
}