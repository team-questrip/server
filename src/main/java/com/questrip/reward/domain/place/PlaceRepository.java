package com.questrip.reward.domain.place;

import com.questrip.reward.storage.PlaceEntity;
import com.questrip.reward.storage.PlaceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlaceRepository {

    private final PlaceJpaRepository placeJpaRepository;

    public Place save(Place place) {
        return placeJpaRepository.save(PlaceEntity.from(place)).toPlace();
    }
}
