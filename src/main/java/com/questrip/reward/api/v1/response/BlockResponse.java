package com.questrip.reward.api.v1.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.questrip.reward.domain.content.ContentBlock;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BlockResponse(
        String type,
        String url,
        String caption,
        String text
) {
    public BlockResponse(ContentBlock.Block block) {
        this(block.getType(), block.getUrl(), block.getCaption(), block.getText());
    }
}
