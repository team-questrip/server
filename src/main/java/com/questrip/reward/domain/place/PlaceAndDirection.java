package com.questrip.reward.domain.place;

import com.questrip.reward.domain.direction.DirectionSummary;
import com.questrip.reward.domain.place.Place;
import lombok.Getter;

@Getter
public class PlaceAndDirection {
    private Place place;
    private DirectionSummary direction;

    public PlaceAndDirection(Place place, DirectionSummary direction) {
        this.place = place;
        this.direction = direction;
    }
}
