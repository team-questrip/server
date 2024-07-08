package com.questrip.reward.api.v1.response;

import com.questrip.reward.domain.content.ContentBlock;

import java.util.List;
import java.util.stream.Collectors;

public record ContentBlockResponse(
        String pageId,
        String language,
        List<BlockResponse> blocks
) {
    public ContentBlockResponse(ContentBlock contentBlock) {
        this(contentBlock.getPageId(),
                contentBlock.getLanguage(),
                contentBlock.getBlocks()
                        .stream()
                        .map(BlockResponse::new)
                        .collect(Collectors.toList())
        );
    }
}
