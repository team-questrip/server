package com.questrip.reward.domain.content;

import com.questrip.reward.client.NotionClient;
import com.questrip.reward.client.response.Block;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContentReader {

    private final ContentRepository contentRepository;
    private final NotionClient notionClient;

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

    public List<ContentBlock.Block> readBlocksFromNotion(String pageId) {
        return notionClient.getBlocks(pageId).results()
                .stream()
                .map(Block::toBlock)
                .collect(Collectors.toList());
    }

    public Optional<ContentBlock> readContentBlock(String pageId, String language) {
        return contentRepository.findContentBlock(pageId, language);
    }

    public Optional<ContentBlock> readDefaultBlock(String pageId) {
        return contentRepository.findContentBlock(pageId, "EN");
    }
}
