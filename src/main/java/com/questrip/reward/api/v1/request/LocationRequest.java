package com.questrip.reward.api.v1.request;

import com.questrip.reward.domain.place.LatLng;
import lombok.Getter;

@Getter
public class LocationRequest {
    private Double latitude;
    private Double longitude;

    public LatLng toLocation() {
        return new LatLng(latitude, longitude);
    }

    public LocationRequest(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
