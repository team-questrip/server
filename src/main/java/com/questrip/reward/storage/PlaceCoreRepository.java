package com.questrip.reward.storage;

import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.place.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlaceCoreRepository implements PlaceRepository {

    private final PlaceMongoRepository placeMongoRepository;

    @Override
    public Place save(Place place) {
        return placeMongoRepository.save(PlaceEntity.from(place)).toPlace();
    }
}
