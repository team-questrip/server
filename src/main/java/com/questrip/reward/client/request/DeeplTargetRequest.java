package com.questrip.reward.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DeeplTargetRequest {
    private List<String> text;
    @JsonProperty("target_lang")
    private String targetLang;

    public DeeplTargetRequest(List<String> text, String targetLang) {
        this.text = text;
        this.targetLang = targetLang;
    }
}
