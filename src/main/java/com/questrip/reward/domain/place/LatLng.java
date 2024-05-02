package com.questrip.reward.domain.place;

import lombok.Getter;

import java.util.Objects;

@Getter
public class LatLng {
    private Double latitude;
    private Double longitude;

    public LatLng(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "%s,%s".formatted(latitude, longitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LatLng latLng)) return false;
        return Objects.equals(getLatitude(), latLng.getLatitude()) && Objects.equals(getLongitude(), latLng.getLongitude());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLatitude(), getLongitude());
    }
}
