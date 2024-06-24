package com.questrip.reward.api.v1.request;

import com.questrip.reward.domain.content.Content;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ContentRequest {
    private String title;
    private Content.Category category;
    private List<String> tags;
    private String images;
    private List<SectionRequest> sections;

    @Getter
    public static class SectionRequest {
        private String title;
        private String content;
        private String image;
        private List<BulletedListRequest> bulletedList;

        public Content.Section toSection() {
            return new Content.Section(
                    title,
                    content,
                    image,
                    bulletedList.stream().map(BulletedListRequest::toBulletedList).collect(Collectors.toList())
            );
        }
    }

    @Getter
    public static class BulletedListRequest {
        private String title;
        private List<String> items;

        public Content.BulletedList toBulletedList() {
            return new Content.BulletedList(title, items);
        }
    }

    public Content toContent() {
        return Content.builder()
                .title(title)
                .category(category)
                .tags(tags)
                .images(images)
                .sections(sections.stream().map(SectionRequest::toSection).collect(Collectors.toList()))
                .build();
    }
}
