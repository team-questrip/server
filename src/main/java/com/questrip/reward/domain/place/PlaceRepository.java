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

    SliceResult<Place> findAllNear(String language, CategoryGroup category, LatLng userLocation, int page, int size);

    SliceResult<Place> findRecommendPlace(LatLng userLocation, List<String> placeIds, int page, int size, String language);

    List<Place> findAllByIdIn(List<String> placeIds, String language);

    Place update(Place place);

    void addTranslateAll(String placeId, Map<String, TranslatedInfo> translations);

    void addTranslateMenuAll(String placeId, Map<String, Set<MenuGroup>> translatedMenuGroups);

    Place findByIdWithLanguage(String id, String language);

    List<Place> findAll();
}
