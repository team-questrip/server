package com.questrip.reward.domain.user;

import com.questrip.reward.fixture.UserFixture;
import com.questrip.reward.storage.mysql.UserJpaRepository;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserLoginProcessorTest {

    @Autowired
    UserLoginProcessor userLoginProcessor;

    @Autowired
    UserAppender userAppender;

    @Autowired
    UserJpaRepository userJpaRepository;

    @AfterEach
    void tearDown() {
        userJpaRepository.deleteAllInBatch();
    }

    @DisplayName("올바른 이메일과 비밀번호를 입력하는 경우 로그인에 성공한다.")
    @Test
    void login() {
        // given
        User initUser = UserFixture.get();
        String password = initUser.getPassword();
        User appended = userAppender.append(initUser);

        // when
        User user = userLoginProcessor.login(initUser.getEmail(), password);

        // then
        assertThat(appended.getId()).isEqualTo(user.getId());
    }

    @DisplayName("존재하지 않는 이메일로 로그인 할 수 없다.")
    @Test
    void login2() {
        assertThatThrownBy(() -> userLoginProcessor.login("test@email.com", "test"))
                .isInstanceOf(GlobalException.class)
                .hasMessageContaining(ErrorCode.USERNAME_NOT_FOUND.getMessage());
    }

    @DisplayName("비밀번호가 일치하지 않을 경우 로그인 할 수 없다.")
    @Test
    void login3() {
        // given
        User initUser = UserFixture.get();
        User appended = userAppender.append(initUser);

        assertThatThrownBy(() -> userLoginProcessor.login(initUser.getEmail(), "test"))
                .isInstanceOf(GlobalException.class)
                .hasMessageContaining(ErrorCode.INVALID_PASSWORD.getMessage());
    }
}