package com.questrip.reward.api.v1.response;

import com.questrip.reward.domain.content.Content;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ContentResponse {
    private String id;
    private String title;
    private Content.Category category;
    private List<String> tags;
    private String images;
    private List<SectionResponse> sections;

    public ContentResponse(Content content) {
        this.id = content.getId();
        this.title = content.getTitle();
        this.category = content.getCategory();
        this.tags = content.getTags();
        this.images = content.getImages();
        this.sections = content.getSections().stream().map(SectionResponse::new).collect(Collectors.toList());
    }

    @Getter
    public static class SectionResponse {
        private String title;
        private String content;
        private String image;
        private List<BulletedListResponse> bulletedList;

        public SectionResponse(Content.Section section) {
            this.title = section.getTitle();
            this.content = section.getContent();
            this.image = section.getImage();
            this.bulletedList = section.getBulletedList() == null ? List.of() : section.getBulletedList().stream().map(BulletedListResponse::new).collect(Collectors.toList());
        }
    }

    @Getter
    public static class BulletedListResponse {
        private String title;
        private List<String> items;

        public BulletedListResponse(Content.BulletedList bulletedList) {
            this.title = bulletedList.getTitle();
            this.items = bulletedList.getItems();
        }
    }
}