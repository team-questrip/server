package com.questrip.reward.domain.content;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Getter
@ToString
public class TranslatedPage {
    private String pageId;
    private String id;
    private String title;
    private List<String> tags;
    private List<String> category;
    private List<String> menuItems;
    private String thumbnailImage;

    public TranslatedPage(Page page, List<String> translatedTexts) {
        this.pageId = page.getId();
        this.id = page.getProperties().getId();
        this.title = translatedTexts.get(0);
        this.tags = Arrays.asList(translatedTexts.get(1).split(","));
        this.category = Arrays.asList(translatedTexts.get(2).split(","));
        this.menuItems = page.getProperties().getMenuItems().getNames();
        this.thumbnailImage = page.getProperties().getThumbnailImage().getFiles().get(0).getFile().getURL();
    }

    public TranslatedPage(String pageId, String id, String title, List<String> tags, List<String> category, List<String> menuItems, String thumbnailImage) {
        this.pageId = pageId;
        this.id = id;
        this.title = title;
        this.tags = tags;
        this.category = category;
        this.menuItems = menuItems;
        this.thumbnailImage = thumbnailImage;
    }
}
