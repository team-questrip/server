package com.questrip.reward.api.v1.response;

import com.questrip.reward.domain.place.Place;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record PlaceListResponse (Set<PlaceResponse> places) {
    public PlaceListResponse(List<Place> places) {
        this(
                places.stream()
                .map(PlaceResponse::new)
                .collect(Collectors.toSet())
        );
    }
}
