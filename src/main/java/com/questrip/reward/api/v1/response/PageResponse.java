package com.questrip.reward.api.v1.response;

import com.questrip.reward.domain.content.Page;
import com.questrip.reward.domain.content.TranslatedPage;

import java.util.List;

public record PageResponse(
        String pageId,
        String id,
        String title,
        List<String> tags,
        List<String> category,
        List<String> menuItems,
        String thumbnailImage
) {
    public PageResponse(Page page) {
        this(
                page.getId(),
                page.getProperties().getId(),
                page.getProperties().getTitle(),
                page.getProperties().getTags().getNames(),
                page.getProperties().getCategory().getNames(),
                page.getProperties().getMenuItems().getNames(),
                page.getProperties().getThumbnailImage().getFiles().get(0).getFile().getURL()
        );
    }

    public PageResponse(TranslatedPage translatedPage) {
        this(
                translatedPage.getPageId(),
                translatedPage.getId(),
                translatedPage.getTitle(),
                translatedPage.getTags(),
                translatedPage.getCategory(),
                translatedPage.getMenuItems(),
                translatedPage.getThumbnailImage()
        );
    }
}
