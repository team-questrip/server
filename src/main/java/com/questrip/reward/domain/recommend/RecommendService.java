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
    private final RecommendUpdater recommendUpdater;
    private final RecommendValidator recommendValidator;
    private final PlaceFinder placeFinder;

    public SliceResult<Place> getRecommendPlaces(Long userId, LatLng userLocation, int page, int size, String language) {
        recommendValidator.validateProgressRecommend(userId);
        return recommendFinder.getRecommends(userId, userLocation, page, size, language);
    }

    public Recommend save(Long userId, String placeId, Recommend.Status status) {
        recommendValidator.validateProgressRecommend(userId);
        Place place = placeFinder.findById(placeId);
        Recommend initRecommend = recommendFactory.init(userId, place, status);

        return recommendAppender.append(place, initRecommend);
    }

    public SliceResult<Recommend> getRecommendsWithStatus(Long userId, List<Recommend.Status> status, int page, int size, String language) {
        return recommendFinder.getRecommendsWithStatus(userId, status, page, size, language);
    }

    public Recommend retrieveProgressRecommend(Long userId) {
        return recommendFinder.retrieveProgressRecommend(userId);
    }

    public Recommend retrieveProgressRecommend(Long userId, String language) {
        return recommendFinder.retrieveProgressRecommend(userId, language);
    }

    public Recommend updateRecommendStatus(Long userId, LatLng userLocation, Recommend.Status status) {
        Recommend recommend = recommendFinder.retrieveProgressRecommend(userId);

        if(status == Recommend.Status.COMPLETED) {
            recommend.complete(userLocation);
        } else {
            recommend.revoke();
        }

        return recommendUpdater.update(recommend);
    }
}
