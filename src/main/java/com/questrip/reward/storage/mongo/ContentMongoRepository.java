package com.questrip.reward.storage.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ContentMongoRepository extends MongoRepository<ContentEntity, String> {

    Optional<ContentEntity> findByPageId(String pageId);
}
