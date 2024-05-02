package com.questrip.reward.domain.place;

import com.questrip.reward.support.response.SliceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceFinder {

    private final PlaceRepository placeRepository;

    public Place findById(String id) {
        return placeRepository.findById(id);
    }

    public SliceResult<Place> findAllNear(LatLng userLocation, int page, int size) {
        return placeRepository.findAllNear(userLocation, page, size);
    }
}
