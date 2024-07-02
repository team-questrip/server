package com.questrip.reward.domain.content;

import com.questrip.reward.client.NotionClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final NotionClient notionClient;
    private final ContentReader contentReader;
    private final ContentTranslator contentTranslator;
    private final ContentUpdater contentUpdater;
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


    public List<Page> getPages() {
        return notionClient.getPageList("183385c0793a49859c61806b09d08cbc")
                .getResults();
    }

    public List<Block> getBlocks(String pageId) {
        return notionClient.getBlocks(pageId)
                .results();
    }

    public List<TranslatedPage> getTranslatedPages(String sourceLang, String targetLang) {
        List<Page> pages = getPages();
        return contentTranslator.translateAllPages(pages, sourceLang, targetLang);
    }

    public List<TranslatedBlock> getTranslatedBlocks(String pageId, String sourceLang, String targetLang) {
        List<Block> blocks = getBlocks(pageId);
        return contentTranslator.translateAllBlocks(blocks, sourceLang, targetLang);
    }

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
}
