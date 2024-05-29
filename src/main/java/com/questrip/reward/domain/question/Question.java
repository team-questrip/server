package com.questrip.reward.domain.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Question {
    private Long id;
    private String userEmail;
    private Category category;
    private String content;

    @Getter
    @AllArgsConstructor
    public enum Category {
        SERVICE_QUESTION("서비스 문의"),
        FEEDBACK("피드백");

        private String description;
    }

    @Builder
    public Question(Long id, String userEmail, Category category, String content) {
        this.id = id;
        this.userEmail = userEmail;
        this.category = category;
        this.content = content;
    }
}
