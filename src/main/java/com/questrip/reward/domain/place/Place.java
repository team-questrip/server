package com.questrip.reward.domain.place;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Place {
    private String id;
    private String googlePlaceId;
    private String placeName;
    private String primaryType;
    private String formattedAddress;
    private LatLng location;
    private PlaceContent content;
    private List<String> openingHours;
    private OpenPeriods openPeriods;
    private List<PlaceImage> images;

    public double calculateDistance(LatLng userLocation) {
        double lat = Math.toRadians(userLocation.getLatitude());
        double lon = Math.toRadians(userLocation.getLongitude());
        double placeLat = Math.toRadians(location.getLatitude());
        double placeLon = Math.toRadians(location.getLongitude());

        double earthRadius = 6371;
        return earthRadius * Math.acos(Math.sin(lat) * Math.sin(placeLat) + Math.cos(lat) * Math.cos(placeLat) * Math.cos(lon - placeLon));
    }

    @Builder
    private Place(String id, String googlePlaceId, String placeName, String primaryType, String formattedAddress, LatLng location, PlaceContent content, List<String> openingHours, List<Period> openPeriods, List<PlaceImage> images) {
        this.id = id;
        this.googlePlaceId = googlePlaceId;
        this.placeName = placeName;
        this.primaryType = primaryType;
        this.formattedAddress = formattedAddress;
        this.content = content;
        this.location = location;
        this.openingHours = openingHours;
        this.openPeriods = new OpenPeriods(openPeriods);
        this.images = images;
    }
}
