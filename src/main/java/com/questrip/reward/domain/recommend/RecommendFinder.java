package com.questrip.reward.domain.recommend;

import com.questrip.reward.domain.place.LatLng;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.place.PlaceFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RecommendFinder {

    private final RecommendRepository recommendRepository;
    private final PlaceFinder placeFinder;

    public List<Place> getRecommends(Long userId, LatLng userLocation) {
        List<String> placeIds = recommendRepository.getExcludePlaceIds(userId, calculateStartDateTime(), calculateEndDateTime());
        return placeFinder.findRecommendPlace(userLocation, placeIds);
    }

    private LocalDateTime calculateStartDateTime() {
        LocalDateTime now = LocalDateTime.now();

        return LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
    }

    private LocalDateTime calculateEndDateTime() {
        LocalDateTime now = LocalDateTime.now();

        return LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 23, 59, 59);
    }
}
