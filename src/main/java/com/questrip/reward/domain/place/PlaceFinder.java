package com.questrip.reward.domain.place;

import com.questrip.reward.support.response.SliceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<Place> findRecommendPlace(LatLng userLocation, List<String> placeIds) {
        return placeRepository.findRecommendPlace(userLocation, placeIds);
    }
}
