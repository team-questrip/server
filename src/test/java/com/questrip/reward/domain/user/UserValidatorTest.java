package com.questrip.reward.domain.user;

import com.questrip.reward.fixture.UserFixture;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserValidatorTest {

    @Autowired
    UserValidator userValidator;

    @Autowired
    UserAppender userAppender;

    @DisplayName("중복된 이메일이 있을 경우 예외가 발생한다.")
    @Test
    void validateEmail() {
        // given
        User append = userAppender.append(UserFixture.get());

        // when & then
        assertThatThrownBy(() -> userValidator.validateEmail(append.getEmail()))
                .isInstanceOf(GlobalException.class)
                .hasMessageContaining(ErrorCode.DUPLICATED_EMAIL.getMessage());
    }
}