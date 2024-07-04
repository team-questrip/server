package com.questrip.reward.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.questrip.reward.api.v1.response.BlockResponse;
import com.questrip.reward.domain.content.ContentBlock;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Block {
    private String type;
    private Image image;
    @JsonProperty("heading_1")
    private Description heading1;
    @JsonProperty("heading_2")
    private Description heading2;
    @JsonProperty("heading_3")
    private Description heading3;
    private Description paragraph;
    @JsonProperty("bulleted_list_item")
    private Description bulletedListItem;

    public ContentBlock.Block toBlock() {
        switch (type) {
            case "image":
                return new ContentBlock.Block(
                        type,
                        image.getFile().getURL(),
                        image.getCaption().isEmpty() ? "" : image.getCaption().get(0).getPlainText(),
                        null
                );
            case "heading_1":
            case "heading_2":
            case "heading_3":
            case "paragraph":
            case "bulleted_list_item":
                return new ContentBlock.Block(
                        type,
                        null,
                        null,
                        getDescription().concat()
                );
            default:
                throw new GlobalException(ErrorCode.UN_SUPPORTED_BLOCK_TYPE,
                        "block type is %s".formatted(type));
        }
    }

    public Description getDescription() {
        switch (type) {
            case "heading_1":
                return heading1;
            case "heading_2":
                return heading2;
            case "heading_3":
                return heading3;
            case "paragraph":
                return paragraph;
            case "bulleted_list_item":
                return bulletedListItem;
            default:
                return null;
        }
    }

    @Getter
    public static class Image {
        private List<RichText> caption;
        private String type;
        private File file;
    }

    public static class File {
        private String url;

        @JsonProperty("url")
        public String getURL() {
            return url;
        }

        @JsonProperty("url")
        public void setURL(String value) {
            this.url = value;
        }
    }

    @Getter
    public static class Description {
        @JsonProperty("rich_text")
        private List<RichText> richText;

        public String concat() {
            return richText.stream()
                    .map(RichText::getPlainText)
                    .collect(Collectors.joining(""));
        }
    }

    @Getter
    public static class RichText {
        @JsonProperty("plain_text")
        private String plainText;
    }
}
