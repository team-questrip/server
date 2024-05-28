package com.questrip.reward.client;

import com.questrip.reward.client.response.GoogleReverseGeocodingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "reverseGeo", url = "https://maps.googleapis.com/maps/api/geocode/json")
public interface GoogleReverseGeocoding {
    @GetMapping
    GoogleReverseGeocodingResponse reverseGeocode(
            @RequestParam(value = "latlng") String latlng,
            @RequestParam(value = "key") String key,
            @RequestParam(value = "language") String language
    );
}
