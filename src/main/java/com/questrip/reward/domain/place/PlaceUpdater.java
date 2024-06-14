package com.questrip.reward.domain.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceUpdater {

    private final PlaceRepository placeRepository;

    public Place update(Place place) {
        return placeRepository.update(place);
    }
}
