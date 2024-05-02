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
