package com.questrip.reward.api.v1.response;

import com.questrip.reward.domain.place.PlaceAndDirection;
import lombok.Getter;

@Getter
public class PlaceAndDirectionResponse {
    private PlaceResponse place;
    private DirectionSummaryResponse directionSummary;

    public PlaceAndDirectionResponse(PlaceAndDirection placeAndDirection) {
        this.place = new PlaceResponse(placeAndDirection.getPlace());
        this.directionSummary = new DirectionSummaryResponse(placeAndDirection.getDirection());
    }
}
