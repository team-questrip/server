package com.questrip.reward.storage;

import com.questrip.reward.domain.place.*;
import com.questrip.reward.storage.mongo.PlaceEntity;
import com.questrip.reward.storage.mongo.PlaceMongoRepository;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import com.questrip.reward.support.response.SliceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

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
        Slice<Place> slice = placeMongoRepository.findAllByLocationNear(toPoint(userLocation), pageRequest)
                .map(PlaceEntity::toPlace);

        return new SliceResult<>(slice);
    }

    @Override
    public List<Place> findRecommendPlace(LatLng userLocation, List<String> placeIds) {
        return placeMongoRepository.findAllByLocationNearAndIdNotIn(toPoint(userLocation), placeIds)
                .stream()
                .map(PlaceEntity::toPlace)
                .collect(Collectors.toList());
    }

    private Point toPoint(LatLng userLocation) {
        return new Point(userLocation.getLongitude(), userLocation.getLatitude());
    }

    @Override
    public List<Place> findAllByIdIn(List<String> placeIds) {
        return placeMongoRepository.findAllByIdIn(placeIds)
                .stream()
                .map(PlaceEntity::toPlace)
                .collect(Collectors.toList());
    }

    @Override
    public Place update(Place place) {
        PlaceEntity entity = placeMongoRepository.findById(place.getId()).orElseThrow();
        entity.update(place);
        return placeMongoRepository.save(entity).toPlace();
    }
}
