package com.questrip.reward.domain.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceAppender placeAppender;
    private final PlaceSearcher placeSearcher;

    public Place save(String googlePlaceId) {
        Place searched = placeSearcher.searchPlace(googlePlaceId);
        return placeAppender.append(searched);
    }
}
