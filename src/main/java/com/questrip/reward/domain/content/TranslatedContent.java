package com.questrip.reward.domain.content;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class TranslatedContent {
    private String pageId;
    private Long id;
    private String title;
    private List<String> tags;
    private List<String> category;
    private List<String> menuItems;
    private String thumbnailImage;
    private String language;

    public TranslatedContent(Content content, String language) {
        TranslatedItem item = content.findItem(language);

        this.pageId = content.getPageId();
        this.id = content.getId();
        this.title = item.getTitle();
        this.tags = item.getTags();
        this.category = item.getCategory();
        this.menuItems = Arrays.asList(content.getMenuItems().split(","));
        this.thumbnailImage = content.getThumbnailImage();
        this.language = language;
    }
}
