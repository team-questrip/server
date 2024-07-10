package com.questrip.reward.domain.recommend;

import com.questrip.reward.domain.place.LatLng;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.place.PlaceFinder;
import com.questrip.reward.support.response.SliceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RecommendFinder {

    private final RecommendRepository recommendRepository;
    private final PlaceFinder placeFinder;
    private final RecommendFactory recommendFactory;

    public SliceResult<Place> getRecommends(Long userId, LatLng userLocation, int page, int size, String language) {
        List<String> placeIds = recommendRepository.getExcludePlaceIds(userId, calculateStartDateTime(), calculateEndDateTime());
        return placeFinder.findRecommendPlace(userLocation, placeIds, page, size, language);
    }

    private LocalDateTime calculateStartDateTime() {
        LocalDateTime now = LocalDateTime.now();

        return LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
    }

    private LocalDateTime calculateEndDateTime() {
        LocalDateTime now = LocalDateTime.now();

        return LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 23, 59, 59);
    }

    public SliceResult<Recommend> getRecommendsWithStatus(Long userId, List<Recommend.Status> status, int page, int size, String language) {
        SliceResult<Recommend> allKeptRecommend = recommendRepository.findAllRecommendsWithStatus(userId, status, page, size);
        Map<String, Place> placeMap = placeFinder.findMapIdIn(extractPlaceIds(allKeptRecommend.getContent()), language);

        return allKeptRecommend.map(recommend -> recommendFactory.of(recommend, placeMap.get(recommend.getPlaceId())));
    }

    private List<String> extractPlaceIds(List<Recommend> recommends) {
        return recommends.stream()
                .map(Recommend::getPlaceId)
                .collect(Collectors.toList());
    }

    public Recommend retrieveProgressRecommend(Long userId) {
        Recommend find = recommendRepository.findProgressRecommend(userId);

        return recommendFactory.from(find);
    }

    public Recommend retrieveProgressRecommend(Long userId, String language) {
        Recommend find = recommendRepository.findProgressRecommend(userId);

        return recommendFactory.from(find, language);
    }
}
