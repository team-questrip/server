package com.questrip.reward.domain.content;

import com.questrip.reward.api.v1.response.BlockResponse;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import lombok.Getter;

@Getter
public class TranslatedBlock {
    private String type;
    private String url;
    private String caption;
    private String text;

    public static TranslatedBlock fromBlock(Block block, String translatedText) {
        switch (block.getType()) {
            case "image":
                return new TranslatedBlock(
                        block.getType(),
                        block.getImage().getFile().getURL(),
                        block.getImage().getCaption().get(0).getPlainText() == null ? "" : block.getImage().getCaption().get(0).getPlainText(),
                        null
                );
            default:
                return new TranslatedBlock(
                        block.getType(),
                        null,
                        null,
                        translatedText
                );
        }
    }

    public TranslatedBlock(String type, String url, String caption, String text) {
        this.type = type;
        this.url = url;
        this.caption = caption;
        this.text = text;
    }
}
