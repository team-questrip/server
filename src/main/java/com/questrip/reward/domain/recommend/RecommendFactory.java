package com.questrip.reward.domain.recommend;

import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.place.PlaceFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecommendFactory {
    private final PlaceFinder placeFinder;

    public Recommend deny(Place place) {
        return Recommend.builder()
                .place(place)
                .status(Recommend.Status.DENIED)
                .build();
    }

    public Recommend keep(Place place) {
        return Recommend.builder()
                .place(place)
                .status(Recommend.Status.KEPT)
                .build();
    }

    public Recommend accept(Place place) {
        return Recommend.builder()
                .place(place)
                .status(Recommend.Status.ACCEPTED)
                .build();
    }

    public Recommend of(Recommend recommend, String placeId) {
        Place place = placeFinder.findById(placeId);

        return Recommend.builder()
                .id(recommend.getId())
                .place(place)
                .status(recommend.getStatus())
                .createdAt(recommend.getCreatedAt())
                .build();
    }
}
