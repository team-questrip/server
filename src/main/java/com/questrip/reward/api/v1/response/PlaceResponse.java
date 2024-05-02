package com.questrip.reward.api.v1.response;

import com.questrip.reward.domain.place.LatLng;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.place.PlaceContent;
import com.questrip.reward.domain.place.PlaceImage;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PlaceResponse {
    private String id;
    private String googlePlaceId;
    private String placeName;
    private String primaryType;
    private String formattedAddress;
    private LatLng location;
    private PlaceContent content;
    private List<PlaceImage> images;
    private List<String> openingHours;

    public PlaceResponse(Place place) {
        this.id = place.getId();
        this.googlePlaceId = place.getGooglePlaceId();
        this.placeName = place.getPlaceName();
        this.primaryType = place.getPrimaryType();
        this.formattedAddress = place.getFormattedAddress();
        this.location = place.getLocation();
        this.content = place.getContent();
        this.images = place.getImages();
        this.openingHours = place.getOpeningHours();
    }
}
