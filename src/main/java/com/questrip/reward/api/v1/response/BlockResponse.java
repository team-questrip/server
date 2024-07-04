package com.questrip.reward.api.v1.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.questrip.reward.client.response.Block;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BlockResponse(
        String type,
        String url,
        String caption,
        String text
) {
    public static BlockResponse fromBlock(Block block) {
        switch (block.getType()) {
            case "image":
                return new BlockResponse(
                        block.getType(),
                        block.getImage().getFile().getURL(),
                        block.getImage().getCaption().isEmpty() ? "" : block.getImage().getCaption().get(0).getPlainText(),
                        null
                );
            case "heading_1":
            case "heading_2":
            case "heading_3":
            case "paragraph":
            case "bulleted_list_item":
                return new BlockResponse(
                        block.getType(),
                        null,
                        null,
                        block.getDescription().concat()
                );
            default:
                throw new GlobalException(ErrorCode.UN_SUPPORTED_BLOCK_TYPE,
                        "block type is %s".formatted(block.getType()));
        }
    }
}
