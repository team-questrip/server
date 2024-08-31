package com.questrip.reward.storage.mongo;

import com.questrip.reward.domain.place.Category;
import com.questrip.reward.domain.place.CategoryGroup;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface PlaceMongoRepository extends MongoRepository<PlaceEntity, String> {
    Slice<PlaceEntity> findAllByLocationNear(Point point, Pageable pageable);

    Slice<PlaceEntity> findAllByLocationNearAndCategoryGroup(Point point, CategoryGroup category, Pageable pageable);

    Slice<PlaceEntity> findAllByLocationNearAndIdNotIn(Point point, List<String> ids, Pageable pageable);

    List<PlaceEntity> findAllByIdIn(List<String> placeIds);

}
