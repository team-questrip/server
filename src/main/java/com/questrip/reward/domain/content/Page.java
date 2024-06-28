package com.questrip.reward.domain.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Page {
    private String id;
    private Properties properties;
    private String url;

    @Getter
    public static class Properties {
        private Category tags;
        private PropertiesTitle title;
        private Category category;
        private ThumbnailImage thumbnailImage;
        private Category menuItems;
        private ID id;

        public String getTitle() {
            return cleanText(title.getRichText().stream()
                    .map(TitleElement::getPlainText)
                    .collect(Collectors.joining()));
        }

        public String getId() {
            return cleanText(id.getTitle().stream().map(TitleElement::getPlainText).collect(Collectors.joining()));
        }
    }

    public static class ThumbnailImage {
        private String id;
        private String type;
        private List<FileElement> files;

        @JsonProperty("id")
        public String getId() { return id; }
        @JsonProperty("id")
        public void setId(String value) { this.id = value; }

        @JsonProperty("type")
        public String getType() { return type; }
        @JsonProperty("type")
        public void setType(String value) { this.type = value; }

        @JsonProperty("files")
        public List<FileElement> getFiles() { return files; }
        @JsonProperty("files")
        public void setFiles(List<FileElement> value) { this.files = value; }
    }

    public static class FileElement {
        private String name;
        private String type;
        private FileFile file;

        @JsonProperty("name")
        public String getName() { return name; }
        @JsonProperty("name")
        public void setName(String value) { this.name = value; }

        @JsonProperty("type")
        public String getType() { return type; }
        @JsonProperty("type")
        public void setType(String value) { this.type = value; }

        @JsonProperty("file")
        public FileFile getFile() { return file; }
        @JsonProperty("file")
        public void setFile(FileFile value) { this.file = value; }
    }

    public static class FileFile {
        private String url;
        @JsonProperty("url")
        public String getURL() { return url; }
        @JsonProperty("url")
        public void setURL(String value) { this.url = value; }
    }

    public static class Category {
        private String id;
        private String type;
        private List<MultiSelect> multiSelect;

        @JsonProperty("id")
        public String getID() { return id; }
        @JsonProperty("id")
        public void setID(String value) { this.id = value; }

        @JsonProperty("type")
        public String getType() { return type; }
        @JsonProperty("type")
        public void setType(String value) { this.type = value; }

        @JsonProperty("multi_select")
        public List<MultiSelect> getMultiSelect() { return multiSelect; }
        @JsonProperty("multi_select")
        public void setMultiSelect(List<MultiSelect> value) { this.multiSelect = value; }

        public List<String> getNames() {
            return multiSelect.stream()
                    .map(MultiSelect::getName)
                    .collect(Collectors.toList());
        }
    }

    public static class MultiSelect {
        private String id;
        private String name;
        private String color;

        @JsonProperty("id")
        public String getID() { return id; }
        @JsonProperty("id")
        public void setID(String value) { this.id = value; }

        @JsonProperty("name")
        public String getName() { return name; }
        @JsonProperty("name")
        public void setName(String value) { this.name = value; }

        @JsonProperty("color")
        public String getColor() { return color; }
        @JsonProperty("color")
        public void setColor(String value) { this.color = value; }
    }

    public static class PropertiesTitle {
        private String id;
        private String type;
        private List<TitleElement> richText;

        @JsonProperty("id")
        public String getID() { return id; }
        @JsonProperty("id")
        public void setID(String value) { this.id = value; }

        @JsonProperty("type")
        public String getType() { return type; }
        @JsonProperty("type")
        public void setType(String value) { this.type = value; }

        @JsonProperty("rich_text")
        public List<TitleElement> getRichText() { return richText; }
        @JsonProperty("rich_text")
        public void setRichText(List<TitleElement> value) { this.richText = value; }
    }

    public static class TitleElement {
        private String type;
        private Text text;
        private String plainText;
        private Object href;

        @JsonProperty("type")
        public String getType() { return type; }
        @JsonProperty("type")
        public void setType(String value) { this.type = value; }

        @JsonProperty("text")
        public Text getText() { return text; }
        @JsonProperty("text")
        public void setText(Text value) { this.text = value; }

        @JsonProperty("plain_text")
        public String getPlainText() { return cleanText(plainText); }
        @JsonProperty("plain_text")
        public void setPlainText(String value) { this.plainText = value; }

        @JsonProperty("href")
        public Object getHref() { return href; }
        @JsonProperty("href")
        public void setHref(Object value) { this.href = value; }
    }

    public static class Text {
        private String content;
        private Object link;

        @JsonProperty("content")
        public String getContent() { return content; }
        @JsonProperty("content")
        public void setContent(String value) { this.content = value; }

        @JsonProperty("link")
        public Object getLink() { return link; }
        @JsonProperty("link")
        public void setLink(Object value) { this.link = value; }
    }

    @Getter
    public static class ID {
        private String id;
        private String type;
        private List<TitleElement> title;
    }

    private static String cleanText(String text) {
        return text.replace("\u00a0", " ");
    }
}