package com.questrip.reward.client;

import com.questrip.reward.client.response.GoogleDirectionSummaryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "googleDirectionClient", url = "https://maps.googleapis.com/maps/api/directions/json")
public interface GoogleDirectionClient {

    @GetMapping
    GoogleDirectionSummaryResponse getDirection(
        @RequestParam(value = "origin") String origin,
        @RequestParam(value = "destination") String destination,
        @RequestParam(value = "mode") String travelMode,
        @RequestParam(value = "key") String key
    );
}
