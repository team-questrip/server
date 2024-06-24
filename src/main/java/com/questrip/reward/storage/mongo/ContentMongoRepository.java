package com.questrip.reward.storage.mongo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContentMongoRepository extends MongoRepository<ContentEntity, String> {

    Slice<ContentEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
