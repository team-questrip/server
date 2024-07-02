package com.questrip.reward.domain.content;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContentReader {

    private final ContentRepository contentRepository;

    public Content read(String pageId) {
        return contentRepository.findByPageId(pageId);
    }

    public List<TranslatedContent> readAllContents(String language) {
        return contentRepository.findAll()
                .stream()
                .map(content -> new TranslatedContent(content, language))
                .filter(translatedContent -> !translatedContent.getTitle().equals("empty"))
                .collect(Collectors.toList());
    }
}
