package com.questrip.reward.fixture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.questrip.reward.domain.content.Content;

import java.util.List;

public class ContentFixture {

    public static Content get() {
        return Content.builder()
                .title("test content")
                .category(Content.Category.FOOD_CULTURE)
                .tags(List.of("boknal", "korea"))
                .images("test images")
                .sections(
                        List.of(
                                new Content.Section("test section", "test section content", "test section image", List.of(
                                        new Content.BulletedList("test bullet", List.of("test boknal"))
                                ))
                        )
                )
                .build();
    }

    public static Content get(String id) {
        return Content.builder()
                .id(id)
                .title("test content")
                .category(Content.Category.FOOD_CULTURE)
                .tags(List.of("boknal", "korea"))
                .images("test images")
                .sections(
                        List.of(
                                new Content.Section("test section", "test section content", "test section image", List.of(
                                        new Content.BulletedList("test bullet", List.of("test boknal"))
                                ))
                        )
                )
                .build();
    }

    public static Content get(int titleNumber) {
        return Content.builder()
                .title("test content" + titleNumber)
                .category(Content.Category.FOOD_CULTURE)
                .tags(List.of("boknal", "korea"))
                .images("test images")
                .sections(
                        List.of(
                                new Content.Section("test section", "test section content", "test section image", List.of(
                                        new Content.BulletedList("test bullet", List.of("test boknal"))
                                ))
                        )
                )
                .build();
    }
}
