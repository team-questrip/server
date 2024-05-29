package com.questrip.reward.storage;

import com.questrip.reward.domain.question.Question;
import com.questrip.reward.domain.question.QuestionRepository;
import com.questrip.reward.storage.mysql.QuestionEntity;
import com.questrip.reward.storage.mysql.QuestionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuestionCoreRepository implements QuestionRepository {

    private final QuestionJpaRepository questionJpaRepository;

    @Override
    public Question save(Question question) {
        return questionJpaRepository.save(QuestionEntity.from(question)).toQuestion();
    }
}
