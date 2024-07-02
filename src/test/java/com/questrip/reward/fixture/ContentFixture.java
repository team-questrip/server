package com.questrip.reward.fixture;

import com.questrip.reward.domain.content.Content;
import com.questrip.reward.domain.content.TranslatedContent;
import com.questrip.reward.domain.content.TranslatedItem;
import com.questrip.reward.domain.content.TranslatedPage;

import java.util.List;

public class ContentFixture {

    public static Content getContent() {
        return Content.builder()
                .id(1L)
                .pageId("pageId")
                .title("Korea's Hot Summer Remedy: Boknal and Samgyetang")
                .tags("태그1")
                .category("카테고리1")
                .menuItems("메뉴1")
                .thumbnailImage("image")
                .build();
    }

    public static TranslatedItem getItem() {
        return new TranslatedItem("Korea's Hot Summer Remedy: Boknal and Samgyetang", List.of("태그1"), List.of("카테고리"), "EN");
    }

    public static TranslatedContent getTranslate() {
        return new TranslatedContent(getContent(), "EN");
    }
}
