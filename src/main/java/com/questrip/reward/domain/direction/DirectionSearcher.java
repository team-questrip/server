package com.questrip.reward.domain.direction;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.questrip.reward.client.GoogleDirectionClient;
import com.questrip.reward.domain.place.LatLng;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DirectionSearcher {

    private final GoogleDirectionClient googleDirectionClient;

    @Value("${google.api.key}")
    private String googleKey;

    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000)
    )
    public DirectionSummary getSummary(LatLng userLocation, String googlePlaceId) {
        return googleDirectionClient.getDirection(userLocation.toString(), parsePlaceId(googlePlaceId), "transit", googleKey).toSummary();
    }

    @Recover
    public DirectionSummary recover(Exception e, LatLng userLocation, String googlePlaceId) {
        log.error("[Google Direction Client] getSummary error!");
        return new DirectionSummary("UNKNOWN", "UNKNOWN");
    }

    private String parsePlaceId(String googlePlaceId) {
        return "place_id:%s".formatted(googlePlaceId);
    }
}
