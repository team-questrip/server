package com.questrip.reward.storage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PlaceMongoRepository extends MongoRepository<PlaceEntity, String> {
    Slice<PlaceEntity> findAllByLocationNear(Point point, Pageable pageable);
}
