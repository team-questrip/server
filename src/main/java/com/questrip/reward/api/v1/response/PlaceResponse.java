package com.questrip.reward.api.v1.response;

import com.questrip.reward.domain.place.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private OpenStatus openNow;
    private List<MenuGroupResponse> menuGroups;

    public PlaceResponse(Place place) {
        this.id = place.getId();
        this.googlePlaceId = place.getGooglePlaceId();
        this.placeName = "%s (%s)".formatted(place.getPlaceName(), place.getRomanizedPlaceName());
        this.primaryType = place.getPrimaryType();
        this.formattedAddress = place.getFormattedAddress();
        this.location = place.getLocation();
        this.content = place.getContent();
        this.images = place.getImages();
        this.openingHours = place.getOpeningHours();
        this.openNow = place.getOpenPeriods().isOpen(LocalDateTime.now());
        this.menuGroups = place.getMenuGroups() == null
                ? List.of()
                : place.getMenuGroups()
                .stream()
                .map(MenuGroupResponse::new)
                .collect(Collectors.toList());
    }
}
