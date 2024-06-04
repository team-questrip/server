package com.questrip.reward.domain.recommend;

import com.questrip.reward.domain.place.LatLng;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.place.PlaceFinder;
import com.questrip.reward.support.response.SliceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RecommendService {
    private final RecommendFinder recommendFinder;
    private final RecommendFactory recommendFactory;
    private final RecommendAppender recommendAppender;
    private final PlaceFinder placeFinder;

    public List<Place> getRecommendPlaces(Long userId, LatLng userLocation) {
        return recommendFinder.getRecommends(userId, userLocation);
    }

    public Recommend save(Long userId, String placeId, Recommend.Status status) {
        Place place = placeFinder.findById(placeId);
        Recommend initRecommend = recommendFactory.init(userId, place, status);

        return recommendAppender.append(place, initRecommend);
    }

    public SliceResult<Recommend> getKeptRecommends(Long userId, int page, int size) {
        return recommendFinder.getKeptRecommends(userId, page, size);
    }
}
