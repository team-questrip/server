package com.questrip.reward.domain.place;

import com.questrip.reward.support.response.SliceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public SliceResult<Place> findAllNear(String language, LatLng userLocation, int page, int size) {
        return placeRepository.findAllNear(language, userLocation, page, size);
    }

    public SliceResult<Place> findRecommendPlace(LatLng userLocation, List<String> placeIds, int page, int size, String language) {
        return placeRepository.findRecommendPlace(userLocation, placeIds, page, size, language);
    }

    public Map<String, Place> findMapIdIn(List<String> placeIds) {
        return placeRepository.findAllByIdIn(placeIds)
                .stream()
                .collect(Collectors.toMap(Place::getId, Function.identity()));
    }

    public Map<String, Place> findMapIdIn(List<String> placeIds, String language) {
        return placeRepository.findAllByIdIn(placeIds, language)
                .stream()
                .collect(Collectors.toMap(Place::getId, Function.identity()));
    }

    public Place findByIdWithLanguage(String id, String language) {
        return placeRepository.findByIdWithLanguage(id, language);
    }

    public List<CategoryGroup> findCategories() {
        return Arrays.stream(CategoryGroup.values()).toList();
    }
}
