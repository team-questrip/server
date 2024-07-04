package com.questrip.reward.storage.mongo;

import com.questrip.reward.domain.content.Content;
import com.questrip.reward.domain.content.TranslatedItem;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "page")
public class ContentEntity {

    @Id
    private String id;
    private String pageId;
    private Long sequence;
    private String title;
    private String tags;
    private String category;
    private String menuItems;
    private String thumbnailImage;
    private List<TranslatedItem> translatedList;

    public Content toContent() {
        return Content.builder()
                .id(sequence)
                .title(title)
                .pageId(pageId)
                .tags(tags)
                .category(category)
                .menuItems(menuItems)
                .thumbnailImage(thumbnailImage)
                .translatedList(translatedList)
                .build();
    }

    public void update(Content content) {
        thumbnailImage = content.getThumbnailImage();
        translatedList = content.getTranslatedList();
    }

    public ContentEntity(String pageId, Long sequence, String title, String tags, String category, String menuItems, String thumbnailImage, List<TranslatedItem> translatedList) {
        this.pageId = pageId;
        this.sequence = sequence;
        this.title = title;
        this.tags = tags;
        this.category = category;
        this.menuItems = menuItems;
        this.thumbnailImage = thumbnailImage;
        this.translatedList = translatedList;
    }
}
