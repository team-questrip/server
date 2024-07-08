package com.questrip.reward.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@ToString
public class DeeplTranslateResponse {
    private List<Translation> translations;

    @JsonProperty("translations")
    public List<Translation> getTranslations() { return translations; }
    @JsonProperty("translations")
    public void setTranslations(List<Translation> value) { this.translations = value; }

    public List<String> getTexts() {
        return translations.stream().map(Translation::getText).collect(Collectors.toList());
    }

    @ToString
    public static class Translation {
        private String detectedSourceLanguage;
        private String text;

        @JsonProperty("detected_source_language")
        public String getDetectedSourceLanguage() { return detectedSourceLanguage; }
        @JsonProperty("detected_source_language")
        public void setDetectedSourceLanguage(String value) { this.detectedSourceLanguage = value; }

        @JsonProperty("text")
        public String getText() { return text; }
        @JsonProperty("text")
        public void setText(String value) { this.text = value; }
    }
}
