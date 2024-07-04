package com.questrip.reward.domain.content;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentReader contentReader;
    private final ContentTranslator contentTranslator;
    private final ContentUpdater contentUpdater;
    private final ContentAppender contentAppender;
    private static final List<String> languageList = List.of(
            "DA",  // Danish
            "DE",  // German
            "EN",  // English
            "ES",  // Spanish
            "FR",  // French
            "IT",  // Italian
            "JA",  // Japanese
            "KO",  // Korean
            "NB",  // Norwegian Bokmål
            "NL",  // Dutch
            "PL",  // Polish
            "PT",  // Portuguese
            "RU",  // Russian
            "SV",  // Swedish
            "ZH"   // Chinese
    );

    public void translateAll(String pageId) {
        Content content = contentReader.read(pageId);
        for(String language : languageList) {
            TranslatedItem item = contentTranslator.translateContent(content, language);
            content.addItem(item);
        }

        contentUpdater.update(content);
    }

    public List<TranslatedContent> findAllTranslatedContent(String language) {
        return contentReader.readAllContents(language);
    }

    public void saveDefaultBlock(String pageId) {
        List<ContentBlock.Block> blocks = contentReader.readBlocksFromNotion(pageId);
        ContentBlock defaultBlock = new ContentBlock(pageId, "en", blocks);
        contentAppender.appendBlock(defaultBlock);
    }
}
