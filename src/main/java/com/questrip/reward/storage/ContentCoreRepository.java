package com.questrip.reward.storage;

import com.questrip.reward.domain.content.Content;
import com.questrip.reward.domain.content.ContentRepository;
import com.questrip.reward.storage.mongo.ContentEntity;
import com.questrip.reward.storage.mongo.ContentMongoRepository;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import com.questrip.reward.support.response.SliceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ContentCoreRepository implements ContentRepository {

    private final ContentMongoRepository contentMongoRepository;
    @Override
    public Content append(Content content) {
        return contentMongoRepository.save(ContentEntity.from(content)).toContent();
    }

    @Override
    public Content findById(String id) {
        return contentMongoRepository.findById(id).orElseThrow(
                () -> new GlobalException(ErrorCode.CONTENT_NOT_FOUND, "content not found. content id is :%s".formatted(id))
        ).toContent();
    }

    @Override
    public SliceResult<Content> findAllBy(int page, int size) {
        Slice<Content> slice = contentMongoRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(page, size))
                .map(ContentEntity::toContent);

        return new SliceResult<Content>(slice);
    }
}
