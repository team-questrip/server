package com.questrip.reward.domain.place;

import com.questrip.reward.client.GooglePlaceClient;
import com.questrip.reward.client.GoogleReverseGeocoding;
import com.questrip.reward.client.response.GooglePlaceSearchResponse;
import com.questrip.reward.client.response.GoogleReverseGeocodingResponse;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlaceSearcher {

    private final GooglePlaceClient googlePlaceClient;
    private final GoogleReverseGeocoding googleReverseGeocoding;

    @Value("${google.api.key}")
    private String googleKey;

    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000)
    )
    public GooglePlaceSearchResponse searchPlace(String googlePlaceId) {
        return googlePlaceClient.placeDetails(googlePlaceId, "*", "ko", googleKey);
    }

    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000)
    )
    public GoogleReverseGeocodingResponse reverseGeocode(LatLng latlng) {
        return googleReverseGeocoding.reverseGeocode(latlng.toString(), googleKey, "en");
    }

    @Recover
    public GooglePlaceSearchResponse recover(Exception e, String googlePlaceId) {
        log.error("[Google place client]: googlePlaceId : {} api call fail", googlePlaceId);
        throw new GlobalException(ErrorCode.EXTERNAL_SERVER_ERROR);
    }

    @Recover
    public GoogleReverseGeocodingResponse recover(Exception e, LatLng latlng) {
        log.error("[Google reverse geocode]: latlng : {} api call fail", latlng);
        return GoogleReverseGeocodingResponse.error();
    }
}
