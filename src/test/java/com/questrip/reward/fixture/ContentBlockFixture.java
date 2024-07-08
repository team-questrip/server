package com.questrip.reward.fixture;

import com.questrip.reward.domain.content.ContentBlock;

import java.util.List;

public class ContentBlockFixture {
    public static ContentBlock get(String pageId, String language) {
        return ContentBlock.builder()
                .pageId(pageId)
                .language(language)
                .blocks(List.of(new ContentBlock.Block("image", "questrips.com", "이미지", null), new ContentBlock.Block("heading1", null, null, "텍스트")))
                .build();
    }
}
