package com.questrip.reward.api.v1.request;

import com.questrip.reward.domain.question.Question;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionRequest {
    @NotNull(message = "문의 사항은 필수입니다.")
    private String content;
    private String userEmail;
    @NotNull(message = "카테고리는 필수입니다.")
    private Question.Category category;

    public QuestionRequest(String content, String userEmail, Question.Category category) {
        this.content = content;
        this.userEmail = userEmail;
        this.category = category;
    }

    public Question toQuestion() {
        return Question.builder()
                .userEmail(userEmail)
                .content(content)
                .category(category)
                .build();
    }
}
