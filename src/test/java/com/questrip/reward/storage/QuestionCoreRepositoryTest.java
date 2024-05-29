package com.questrip.reward.storage;

import com.questrip.reward.domain.question.Question;
import com.questrip.reward.storage.mysql.QuestionEntity;
import com.questrip.reward.storage.mysql.QuestionJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class QuestionCoreRepositoryTest {

    @Autowired
    QuestionCoreRepository questionCoreRepository;

    @Autowired
    QuestionJpaRepository jpaRepository;

    @AfterEach
    void tearDown() {
        jpaRepository.deleteAllInBatch();
    }

    @DisplayName("문의가 저장된다.")
    @Test
    void save() {
        // given
        Question question = Question.builder()
                .content("문의사항입니다.")
                .build();

        // when
        Question saved = questionCoreRepository.save(question);

        // then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getContent()).isEqualTo(question.getContent());
    }

    @DisplayName("긴 내용의 문의도 저장할 수 있다.")
    @Test
    void save2() {
        // given
        Question question = Question.builder()
                .content("이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.이것은 1000자가 넘습니다.")
                .build();

        // when
        Question saved = questionCoreRepository.save(question);

        // then
        List<QuestionEntity> entities = jpaRepository.findAll();
        assertThat(entities.size()).isEqualTo(1);
    }
}