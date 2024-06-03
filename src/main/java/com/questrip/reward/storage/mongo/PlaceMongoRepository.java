package com.questrip.reward.storage.mongo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface PlaceMongoRepository extends MongoRepository<PlaceEntity, String> {
    Slice<PlaceEntity> findAllByLocationNear(Point point, Pageable pageable);

    List<PlaceEntity> findAllByLocationNearAndIdNotIn(Point point, List<String> ids);
}
