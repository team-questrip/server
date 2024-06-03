package com.questrip.reward.domain.recommend;

import com.questrip.reward.domain.place.LatLng;
import com.questrip.reward.domain.place.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RecommendService {
    private final RecommendFinder recommendFinder;

    public List<Place> getRecommendPlaces(Long userId, LatLng userLocation) {
        return recommendFinder.getRecommends(userId, userLocation);
    }
}
