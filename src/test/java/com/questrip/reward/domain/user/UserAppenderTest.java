package com.questrip.reward.domain.user;

import com.questrip.reward.fixture.UserFixture;
import com.questrip.reward.security.jwt.JwtUtils;
import com.questrip.reward.storage.mysql.UserEntity;
import com.questrip.reward.storage.mysql.UserJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserAppenderTest {

    @Autowired
    UserAppender userAppender;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserJpaRepository userJpaRepository;

    @AfterEach
    void tearDown() {
        userJpaRepository.deleteAllInBatch();
    }

    @DisplayName("유저를 등록한다.")
    @Test
    void append() {
        // given
        User user = UserFixture.get();

        // when
        User appended = userAppender.append(user);

        // then
        assertThat(appended.getId()).isNotNull();

        List<UserEntity> entities = userJpaRepository.findAll();
        assertThat(entities.size()).isOne();
    }

    @DisplayName("등록된 유저의 비밀번호는 암호화된다.")
    @Test
    void append2() {
        // given
        User user = UserFixture.get();
        String password = user.getPassword();

        // when
        User appended = userAppender.append(user);

        // then
        assertThat(appended.getPassword()).isNotEqualTo(password);
        assertThat(passwordEncoder.matches(password, appended.getPassword())).isTrue();
    }

    @DisplayName("유저 등록 시 refreshToken이 발급된다.")
    @Test
    void append3() {
        // given
        User user = UserFixture.get();

        // when
        User appended = userAppender.append(user);

        // then
        assertThat(appended.getRefreshToken()).isNotNull();
    }

    @DisplayName("유저 등록 시 refreshToken이 발급된다.")
    @Test
    void append4() {
        // given
        User user = UserFixture.get();

        // when
        User appended = userAppender.append(user);

        // then
        String refreshToken = userJpaRepository.findById(appended.getId()).get().getRefreshToken();
        assertThat(appended.getRefreshToken()).isNotNull();
        assertThat(appended.getRefreshToken()).isEqualTo(refreshToken);
    }
}