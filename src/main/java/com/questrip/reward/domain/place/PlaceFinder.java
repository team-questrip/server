package com.questrip.reward.domain.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceFinder {

    private final PlaceRepository placeRepository;

    public Place findById(String id) {
        return placeRepository.findById(id);
    }
}
