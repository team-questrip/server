package com.questrip.reward.domain.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionAppender {

    private final QuestionRepository questionRepository;

    public Question append(Question question) {
        return questionRepository.save(question);
    }

}
