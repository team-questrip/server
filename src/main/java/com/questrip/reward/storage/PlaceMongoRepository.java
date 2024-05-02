package com.questrip.reward.storage;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaceMongoRepository extends MongoRepository<PlaceEntity, String> {
}
