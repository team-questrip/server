package com.questrip.reward.domain.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionAppender questionAppender;

    public Question registerQuestion(Question question) {
        return questionAppender.append(question);
    }
}
