package com.questrip.reward.storage.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ContentBlockMongoRepository extends MongoRepository<ContentBlockEntity, String> {
    Optional<ContentBlockEntity> findByPageIdAndLanguage(String pageId, String language);
}
