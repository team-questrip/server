package com.questrip.reward.domain.content;

import com.questrip.reward.api.v1.request.DeeplTranslateRequest;
import com.questrip.reward.client.DeeplTranslateClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContentTranslator {

    private final DeeplTranslateClient client;

    public List<TranslatedPage> translateAllPages(List<Page> pages, String sourceLang, String targetLang) {
        return pages.stream()
                .map(page -> translatePage(page, sourceLang, targetLang))
                .collect(Collectors.toList());
    }

    private TranslatedPage translatePage(Page page, String sourceLang, String targetLang) {
        List<String> candidates = List.of(page.getProperties().getTitle(),
                page.getProperties().getTagsToString(),
                page.getProperties().getCategoriesToString());
        List<String> translatedTexts = client.getTranslate(new DeeplTranslateRequest(candidates, sourceLang, targetLang))
                .getTexts();

        return new TranslatedPage(page, translatedTexts);
    }

    public List<TranslatedBlock> translateAllBlocks(List<Block> blocks, String sourceLang, String targetLang) {
        List<String> candidates = blocks.stream().map(block -> getBlockContent(block))
                .filter(content -> !content.equals("image"))
                .collect(Collectors.toList());

        List<String> translatedTexts = client.getTranslate(new DeeplTranslateRequest(candidates, sourceLang, targetLang)).getTexts();
        int idx = 0;
        List<TranslatedBlock> result = new ArrayList<>();
        for (var block : blocks) {
            if(block.getType().equals("image")) {
                result.add(TranslatedBlock.fromBlock(block, ""));
                continue;
            }

            result.add(TranslatedBlock.fromBlock(block, translatedTexts.get(idx++)));
        }

        return result;
    }

    private String getBlockContent(Block block) {
        String type = block.getType();
        return switch (type) {
            case "heading_1" -> block.getHeading1().concat();
            case "heading_2" -> block.getHeading2().concat();
            case "heading_3" -> block.getHeading3().concat();
            case "paragraph" -> block.getParagraph().concat();
            case "bulleted_list_item" -> block.getBulletedListItem().concat();
            default -> "image";
        };
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
}
