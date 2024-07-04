package com.questrip.reward.domain.content;

import com.questrip.reward.storage.mongo.ContentBlockEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ContentBlock {
    private String pageId;
    private String language;
    private List<Block> blocks;

    @Data
    public static class Block {
        private String type;
        private String url;
        private String caption;
        private String text;

        public Block(String type, String url, String caption, String text) {
            this.type = type;
            this.url = url;
            this.caption = caption;
            this.text = text;
        }
    }

    @Builder
    public ContentBlock(String pageId, String language, List<Block> blocks) {
        this.pageId = pageId;
        this.language = language;
        this.blocks = blocks == null ? new ArrayList<>() : blocks;
    }
}
