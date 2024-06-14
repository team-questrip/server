package com.questrip.reward.domain.recommend;

import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.place.PlaceFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecommendFactory {
    private final PlaceFinder placeFinder;

    public Recommend init(Long userId, Place place, Recommend.Status status) {
        return Recommend.builder()
                .place(place)
                .userId(userId)
                .status(status)
                .build();
    }

    public Recommend from(Recommend recommend) {
        Place place = placeFinder.findById(recommend.getPlaceId());

        return Recommend.builder()
                .id(recommend.getId())
                .place(place)
                .userId(recommend.getUserId())
                .status(recommend.getStatus())
                .createdAt(recommend.getCreatedAt())
                .build();
    }

    public Recommend of(Recommend recommend, Place place) {
        return Recommend.builder()
                .id(recommend.getId())
                .place(place)
                .userId(recommend.getUserId())
                .status(recommend.getStatus())
                .createdAt(recommend.getCreatedAt())
                .build();
    }
}
