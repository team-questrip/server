package com.questrip.reward.api.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DeeplTranslateRequest {
    private List<String> text;
    @JsonProperty("source_lang")
    private String sourceLang;
    @JsonProperty("target_lang")
    private String targetLang;

    public DeeplTranslateRequest(List<String> text, String sourceLang, String targetLang) {
        this.text = text;
        this.sourceLang = sourceLang;
        this.targetLang = targetLang;
    }
}
