package com.questrip.reward.storage;

import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.place.PlaceRepository;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
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

    @Override
    public Place findById(String id) {
        return placeMongoRepository.findById(id)
                .orElseThrow(() -> new GlobalException(ErrorCode.NOT_EXIST_PLACE, "place id %s is not found.".formatted(id)))
                .toPlace();
    }
}
