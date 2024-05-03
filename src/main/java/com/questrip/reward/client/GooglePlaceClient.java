package com.questrip.reward.client;

import com.questrip.reward.client.response.GooglePlaceSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "googleMapsApi", url = "https://places.googleapis.com/v1/places/")
public interface GooglePlaceClient {

    @GetMapping(value = "{placeId}")
    GooglePlaceSearchResponse placeDetails(@PathVariable String placeId,
                                           @RequestParam(value = "fields") String fields,
                                           @RequestParam(value = "languageCode") String languageCode,
                                           @RequestParam(value = "key") String key);

}