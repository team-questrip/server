package com.questrip.reward.domain.content;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
public class Content {
    private String id;
    private String title;
    private Category category;
    private List<String> tags;
    private String images;
    private List<Section> sections;

    @Getter
    @NoArgsConstructor
    public static class Section {
        private String title;
        private String content;
        private String image;
        private List<BulletedList> bulletedList;

        public Section(String title, String content, String image, List<BulletedList> bulletedList) {
            this.title = title;
            this.content = content;
            this.image = image;
            this.bulletedList = bulletedList;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Section section)) return false;
            return Objects.equals(getTitle(), section.getTitle()) && Objects.equals(getContent(), section.getContent()) && Objects.equals(getImage(), section.getImage()) && Objects.equals(getBulletedList(), section.getBulletedList());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getTitle(), getContent(), getImage(), getBulletedList());
        }
    }

    @Getter
    @NoArgsConstructor
    public static class BulletedList {
        private String title;
        private List<String> items;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BulletedList that)) return false;
            return Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getItems(), that.getItems());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getTitle(), getItems());
        }

        public BulletedList(String title, List<String> items) {
            this.title = title;
            this.items = items;
        }
    }

    @Builder
    public Content(String id, String title, Category category, List<String> tags, String images, List<Section> sections) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.tags = tags;
        this.images = images;
        this.sections = sections;
    }

    public enum Category {
        FOOD_CULTURE,
    }
}