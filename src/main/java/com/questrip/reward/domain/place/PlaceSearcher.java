package com.questrip.reward.domain.place;

import com.questrip.reward.client.GooglePlaceClient;
import com.questrip.reward.client.response.GooglePlaceSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceSearcher {

    private final GooglePlaceClient googlePlaceClient;

    @Value("${google.api.key}")
    private String googleKey;

    public GooglePlaceSearchResponse searchPlace(String googlePlaceId) {
        return googlePlaceClient.placeDetails(googlePlaceId, "*", "en", googleKey);
    }
}
