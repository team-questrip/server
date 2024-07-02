package com.questrip.reward.api.v1.response;

import com.questrip.reward.domain.content.Page;
import com.questrip.reward.domain.content.TranslatedContent;
import com.questrip.reward.domain.content.TranslatedPage;

import java.util.List;

public record ContentResponse(
        String pageId,
        Long id,
        String title,
        List<String> tags,
        List<String> category,
        List<String> menuItems,
        String thumbnailImage
) {
    public ContentResponse(TranslatedContent content) {
        this(
                content.getPageId(),
                content.getId(),
                content.getTitle(),
                content.getTags(),
                content.getCategory(),
                content.getMenuItems(),
                content.getThumbnailImage()
        );
    }
}
