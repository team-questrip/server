package com.questrip.reward.domain.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceAppender {

    private final PlaceRepository placeRepository;
    private final PlaceTranslator placeTranslator;

    public Place append(Place place) {
        return placeRepository.save(place);
    }
}
