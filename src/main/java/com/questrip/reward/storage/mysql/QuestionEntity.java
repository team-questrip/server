package com.questrip.reward.storage.mysql;

import com.questrip.reward.domain.question.Question;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "question")
public class QuestionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;
    @Enumerated(EnumType.STRING)
    private Question.Category category;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    public static QuestionEntity from(Question question) {
        return QuestionEntity.builder()
                .content(question.getContent())
                .category(question.getCategory())
                .userEmail(question.getUserEmail())
                .build();
    }

    public Question toQuestion() {
        return Question.builder()
                .id(id)
                .userEmail(userEmail)
                .category(category)
                .content(content)
                .build();
    }

    @Builder

    public QuestionEntity(String userEmail, Question.Category category, String content) {
        this.userEmail = userEmail;
        this.category = category;
        this.content = content;
    }
}
