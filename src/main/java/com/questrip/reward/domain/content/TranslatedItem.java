package com.questrip.reward.domain.content;

import lombok.Getter;

import java.util.List;

@Getter
public class TranslatedItem {
    private String title;
    private List<String> tags;
    private List<String> category;
    private String language;

    public boolean support(String language) {
        return this.language.equals(language);
    }

    public TranslatedItem(String title, List<String> tags, List<String> category, String language) {
        this.title = title;
        this.tags = tags;
        this.category = category;
        this.language = language;
    }
}
