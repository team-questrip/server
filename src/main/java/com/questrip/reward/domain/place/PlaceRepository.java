package com.questrip.reward.domain.place;

import com.questrip.reward.support.response.SliceResult;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface PlaceRepository {
    Place save(Place place);

    Place findById(String id);

    SliceResult<Place> findAllNear(LatLng userLocation, int page, int size);

    List<Place> findRecommendPlace(LatLng userLocation, List<String> placeIds);

    List<Place> findAllByIdIn(List<String> placeIds);

    Place update(Place place);

    void addTranslateAll(String placeId, Map<String, TranslatedInfo> translations);

    void addTranslateMenuAll(String placeId, Map<String, Set<MenuGroup>> translatedMenuGroups);
}
