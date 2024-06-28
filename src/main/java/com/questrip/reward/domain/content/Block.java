package com.questrip.reward.domain.content;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Getter
    public static class Image {
        private List<RichText> caption;
        private String type;
        private File file;
    }

    public static class File {
        private String url;

        @JsonProperty("url")
        public String getURL() { return url; }
        @JsonProperty("url")
        public void setURL(String value) { this.url = value; }
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
