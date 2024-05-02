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
    private List<String> openingHours;
    private OpenPeriods openPeriods;

    @Builder
    private Place(String id, String googlePlaceId, String placeName, String primaryType, String formattedAddress, LatLng location, List<String> openingHours, List<Period> openPeriods) {
        this.id = id;
        this.googlePlaceId = googlePlaceId;
        this.placeName = placeName;
        this.primaryType = primaryType;
        this.formattedAddress = formattedAddress;
        this.location = location;
        this.openingHours = openingHours;
        this.openPeriods = new OpenPeriods(openPeriods);
    }
}
