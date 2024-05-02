package com.questrip.reward.storage;

import com.questrip.reward.domain.place.LatLng;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.place.PlaceRepository;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import com.questrip.reward.support.response.SliceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.geo.Point;
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

    @Override
    public SliceResult<Place> findAllNear(LatLng userLocation, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Slice<Place> slice = placeMongoRepository.findAllByLocationNear(new Point(userLocation.getLongitude(), userLocation.getLatitude()), pageRequest)
                .map(PlaceEntity::toPlace);

        return new SliceResult<>(slice);
    }
}
