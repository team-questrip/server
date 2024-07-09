package com.questrip.reward.domain.content;

import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContentService {

    private final ContentReader contentReader;
    private final ContentTranslator contentTranslator;
    private final ContentUpdater contentUpdater;
    private final ContentAppender contentAppender;
    private final ContentImageUploader contentImageUploader;

    public void init(String pageId) {
        log.info("pageId: {} start init", pageId);
        if(contentReader.readDefaultBlock(pageId).isPresent()) {
            log.info("pageId: {} already initialized. process quit.", pageId);
            return;
        }

        Content content = contentReader.read(pageId);
        List<ContentBlock.Block> notionBlocks = contentReader.readBlocksFromNotion(pageId);

        CompletableFuture<String> thumbnailFuture = uploadThumbnail(content);
        List<CompletableFuture<ContentBlock.Block>> blockFutures = processBlocks(notionBlocks, content.getId());

        CompletableFuture.allOf(
                        CompletableFuture.allOf(blockFutures.toArray(new CompletableFuture[0])),
                        thumbnailFuture
                ).thenAccept(v -> {
                    updateContentAndBlocks(content, pageId, thumbnailFuture, blockFutures);
                    contentTranslator.translateAll(content);
                }).exceptionally(this::handleException);
    }

    private CompletableFuture<String> uploadThumbnail(Content content) {
        return contentImageUploader.uploadImage(content.getThumbnailImage(), content.getId());
    }

    private List<CompletableFuture<ContentBlock.Block>> processBlocks(List<ContentBlock.Block> blocks, Long contentId) {
        return blocks.stream()
                .map(block -> processBlock(block, contentId))
                .collect(Collectors.toList());
    }

    private CompletableFuture<ContentBlock.Block> processBlock(ContentBlock.Block block, Long contentId) {
        if ("image".equals(block.getType())) {
            return contentImageUploader.uploadImage(block.getUrl(), contentId)
                    .thenApply(newImageUrl -> new ContentBlock.Block(block.getType(), newImageUrl, block.getCaption(), null));
        } else {
            return CompletableFuture.completedFuture(block);
        }
    }

    private void updateContentAndBlocks(Content content, String pageId,
                                        CompletableFuture<String> thumbnailFuture,
                                        List<CompletableFuture<ContentBlock.Block>> blockFutures) {
        content.updateImage(thumbnailFuture.join());
        contentUpdater.update(content);

        List<ContentBlock.Block> updatedBlocks = blockFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        ContentBlock updatedContentBlock = new ContentBlock(pageId, "EN", updatedBlocks);
        contentAppender.appendBlock(updatedContentBlock);
    }

    private Void handleException(Throwable e) {
        log.error("Error during initialization", e);
        return null;
    }

    public List<TranslatedContent> findAllTranslatedContent(String language) {
        return contentReader.readAllContents(language);
    }

    public ContentBlock getBlocks(String pageId, String language) {
        return contentReader.readContentBlock(pageId, language)
                .orElseGet(() -> {
                    ContentBlock defaultBlock = contentReader.readDefaultBlock(pageId)
                            .orElseThrow(() -> new GlobalException(ErrorCode.CONTENT_NOT_FOUND, "request pageId is : %s".formatted(pageId)));

                    ContentBlock translatedBlock = contentTranslator.translateAllBlocks(defaultBlock.getBlocks(), pageId, language);
                    contentAppender.appendBlock(translatedBlock);

                    return translatedBlock;
                });
    }
}
