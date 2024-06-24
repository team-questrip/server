package com.questrip.reward.storage.mongo;

import com.questrip.reward.domain.content.Content;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "content")
public class ContentEntity extends BaseEntity {
    @Id
    private String id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Content.Category category;
    private List<String> tags;
    private String images;
    private List<Content.Section> sections;

    public static ContentEntity from(Content content) {
        return ContentEntity.builder()
                .title(content.getTitle())
                .category(content.getCategory())
                .tags(content.getTags())
                .images(content.getImages())
                .sections(content.getSections())
                .build();
    }

    public Content toContent() {
        return Content.builder()
                .id(id)
                .title(title)
                .category(category)
                .tags(tags)
                .images(images)
                .sections(sections)
                .build();
    }

    @Builder
    public ContentEntity(String title, Content.Category category, List<String> tags, String images, List<Content.Section> sections) {
        this.title = title;
        this.category = category;
        this.tags = tags;
        this.images = images;
        this.sections = sections;
    }
}
