package com.questrip.reward.domain.question;

import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository {

    Question save(Question question);
}
