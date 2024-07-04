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

    public void init(String pageId) {
        log.info("pageId: {} start init", pageId);
        if(contentReader.readDefaultBlock(pageId).isPresent()) {
            log.info("pageId: {} already initialized. process quit.", pageId);
            return;
        }

        // Content 읽기
        Content content = contentReader.read(pageId);

        // 썸네일 이미지 업로드
        CompletableFuture<String> thumbnailFuture = contentImageUploader.uploadImage(content.getThumbnailImage(), content.getId());

        // Notion에서 블록 읽기
        List<ContentBlock.Block> notionBlocks = contentReader.readBlocksFromNotion(pageId);

        // 블록 처리 (이미지 업로드 포함)
        List<CompletableFuture<ContentBlock.Block>> blockFutures = notionBlocks.stream()
                .map(block -> {
                    if ("image".equals(block.getType())) {
                        return contentImageUploader.uploadImage(block.getUrl(), content.getId())
                                .thenApply(newImageUrl -> new ContentBlock.Block(block.getType(), newImageUrl, block.getCaption(), null));
                    } else {
                        // 이미지가 아닌 블록은 그대로 유지
                        return CompletableFuture.completedFuture(block);
                    }
                })
                .collect(Collectors.toList());

        // 모든 비동기 작업이 완료될 때까지 기다림
        CompletableFuture.allOf(
                CompletableFuture.allOf(blockFutures.toArray(new CompletableFuture[0])),
                thumbnailFuture
        ).thenAccept(v -> {
            // 썸네일 URL 업데이트
            content.updateImage(thumbnailFuture.join());
            contentUpdater.update(content);

            // 모든 블록 (이미지 및 비이미지)을 포함하여 ContentBlock 생성 및 저장
            List<ContentBlock.Block> updatedBlocks = blockFutures.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList());
            ContentBlock updatedContentBlock = new ContentBlock(pageId, "EN", updatedBlocks);
            contentAppender.appendBlock(updatedContentBlock);

            // 모든 언어로 번역
            translateAll(pageId);
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        }).join();
    }

    private void translateAll(String pageId) {
        Content content = contentReader.read(pageId);
        if(content.getTranslatedList().size() == languageList.size()) {
            return;
        }

        List<String> alreadyTranslated = content.getTranslatedList().stream().map(TranslatedItem::getLanguage).collect(Collectors.toList());

        List<CompletableFuture<TranslatedItem>> futures = languageList.stream()
                .filter(language -> !alreadyTranslated.contains(language))
                .map(language -> CompletableFuture.supplyAsync(() ->
                        contentTranslator.translateContent(content, language)))
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
