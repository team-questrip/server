package com.questrip.reward.domain.direction;

import com.questrip.reward.client.GoogleDirectionClient;
import com.questrip.reward.domain.place.LatLng;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DirectionSearcher {

    private final GoogleDirectionClient googleDirectionClient;

    @Value("${google.api.key}")
    private String googleKey;

    public DirectionSummary getSummary(LatLng userLocation, String googlePlaceId) {
        try {
            return googleDirectionClient.getDirection(userLocation.toString(), parsePlaceId(googlePlaceId), "transit", googleKey).toSummary();
        } catch (Exception e) {
            log.error("googleDirectionClient getSummary error!");
            return new DirectionSummary("UNKNOWN", "UNKNOWN");
        }
    }

    private String parsePlaceId(String googlePlaceId) {
        return "place_id:%s".formatted(googlePlaceId);
    }
}
