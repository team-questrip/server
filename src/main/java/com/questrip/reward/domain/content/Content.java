package com.questrip.reward.domain.content;

import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Content {
    private String pageId;
    private Long id;
    private String title;
    private String tags;
    private String category;
    private String menuItems;
    private String thumbnailImage;
    private List<TranslatedItem> translatedList;

    @Builder
    public Content(String pageId, Long id, String title, String tags, String category, String menuItems, String thumbnailImage, List<TranslatedItem> translatedList) {
        this.pageId = pageId;
        this.id = id;
        this.title = title;
        this.tags = tags;
        this.category = category;
        this.menuItems = menuItems;
        this.thumbnailImage = thumbnailImage;
        this.translatedList = translatedList == null ? new ArrayList<>() : translatedList;
    }

    public void addItem(TranslatedItem translated) {
        translatedList.add(translated);
    }

    public TranslatedItem findItem(String language) {
        return translatedList.stream()
                .filter(item -> item.support(language))
                .findFirst()
                .orElseGet(() -> new TranslatedItem("empty", List.of("empty"), List.of("empty"), language));
    }
}
