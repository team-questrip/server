package com.questrip.reward.domain.content;

import com.questrip.reward.api.v1.request.DeeplTranslateRequest;
import com.questrip.reward.client.DeeplTranslateClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContentTranslator {

    private final DeeplTranslateClient client;
    private final ContentUpdater contentUpdater;

    private static final List<String> LANGUAGE_LIST = List.of(
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

    public ContentBlock translateAllBlocks(List<ContentBlock.Block> blocks, String pageId, String targetLang) {
        List<String> candidates = blocks.stream()
                .filter(block -> !block.getType().equals("image"))
                .filter(block -> !block.getText().isEmpty())
                .map(ContentBlock.Block::getText)
                .collect(Collectors.toList());

        List<String> translatedTexts = client.getTranslate(new DeeplTranslateRequest(candidates, "EN", targetLang)).getTexts();
        int idx = 0;
        List<ContentBlock.Block> result = new ArrayList<>();
        for (var block : blocks) {
            if(block.getType().equals("image") || block.getText().isEmpty()) {
                result.add(block);
                continue;
            }

            result.add(new ContentBlock.Block(block.getType(), null, null, translatedTexts.get(idx++)));
        }

        return new ContentBlock(pageId, targetLang, result);
    }

    public TranslatedItem translateContent(Content content, String language) {
        List<String> candidates = List.of(content.getTitle(), content.getTags(), content.getCategory());

        List<String> translatedTexts = client.getTranslate(new DeeplTranslateRequest(candidates, "en", language)).getTexts();

        return new TranslatedItem(
                translatedTexts.get(0),
                Arrays.asList(translatedTexts.get(1).split(",")),
                Arrays.asList(translatedTexts.get(2).split(",")),
                language
        );
    }

    public void translateAll(Content content) {
        if(content.getTranslatedList().size() == LANGUAGE_LIST.size()) {
            return;
        }

        List<String> alreadyTranslated = content.getTranslatedList().stream().map(TranslatedItem::getLanguage).collect(Collectors.toList());

        List<CompletableFuture<TranslatedItem>> futures = LANGUAGE_LIST.stream()
                .filter(language -> !alreadyTranslated.contains(language))
                .map(language -> CompletableFuture.supplyAsync(() ->
                        translateContent(content, language)))
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()))
                .thenAccept(translatedItems -> {
                    translatedItems.forEach(content::addItem);
                    contentUpdater.update(content);
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    return null;
                });
    }
}
