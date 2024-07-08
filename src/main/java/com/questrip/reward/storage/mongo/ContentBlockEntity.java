package com.questrip.reward.storage.mongo;

import com.questrip.reward.domain.content.ContentBlock;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "block")
public class ContentBlockEntity {
    @Id
    private String id;
    private String pageId;
    private String language;
    private List<ContentBlock.Block> blocks = new ArrayList<>();

    public ContentBlock toBlock() {
        return ContentBlock.builder()
                .pageId(pageId)
                .language(language)
                .blocks(blocks)
                .build();
    }

    public static ContentBlockEntity from(ContentBlock contentBlock) {
        return ContentBlockEntity.builder()
                .pageId(contentBlock.getPageId())
                .language(contentBlock.getLanguage())
                .blocks(contentBlock.getBlocks())
                .build();
    }

    @Builder
    public ContentBlockEntity(String id, String pageId, String language, List<ContentBlock.Block> blocks) {
        this.id = id;
        this.pageId = pageId;
        this.language = language;
        this.blocks = blocks;
    }
}
