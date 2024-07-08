package com.questrip.reward.storage;

import com.questrip.reward.domain.content.Content;
import com.questrip.reward.domain.content.ContentBlock;
import com.questrip.reward.domain.content.ContentRepository;
import com.questrip.reward.storage.mongo.ContentBlockEntity;
import com.questrip.reward.storage.mongo.ContentBlockMongoRepository;
import com.questrip.reward.storage.mongo.ContentEntity;
import com.questrip.reward.storage.mongo.ContentMongoRepository;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ContentCoreRepository implements ContentRepository {

    private final ContentMongoRepository contentMongoRepository;
    private final ContentBlockMongoRepository contentBlockMongoRepository;

    @Override
    public Content findByPageId(String pageId) {
        return contentMongoRepository.findByPageId(pageId)
                .orElseThrow(() -> new GlobalException(ErrorCode.CONTENT_NOT_FOUND))
                .toContent();
    }

    @Override
    public void update(Content content) {
        ContentEntity entity = contentMongoRepository.findByPageId(content.getPageId())
                .orElseThrow();
        entity.update(content);
        contentMongoRepository.save(entity);
    }

    @Override
    public List<Content> findAll() {
        return contentMongoRepository.findAll()
                .stream()
                .map(ContentEntity::toContent)
                .collect(Collectors.toList());
    }

    @Override
    public ContentBlock saveBlock(ContentBlock contentBlock) {
        return contentBlockMongoRepository.save(ContentBlockEntity.from(contentBlock))
                .toBlock();
    }

    @Override
    public Optional<ContentBlock> findContentBlock(String pageId, String language) {
        return contentBlockMongoRepository.findByPageIdAndLanguage(pageId, language)
                .map(ContentBlockEntity::toBlock);
    }
}
