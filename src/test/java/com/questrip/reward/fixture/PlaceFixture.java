package com.questrip.reward.fixture;

import com.questrip.reward.domain.place.LatLng;
import com.questrip.reward.domain.place.Place;

import java.util.List;

public class PlaceFixture {
    public static Place get() {
        return Place.builder()
                .googlePlaceId("placeId")
                .placeName("test")
                .primaryType("test")
                .formattedAddress("test")
                .location(new LatLng(1.23, 4.56))
                .openingHours(List.of("Monday: 5:00PM-2:00AM",
                        "Tuesday: 5:00PM-2:00AM",
                        "Wednesday: 5:00PM-2:00AM",
                        "Thursday: 5:00PM-2:00AM",
                        "Friday: 5:00PM-2:00AM",
                        "Saturday: 5:00PM-2:00AM",
                        "Sunday: 5:00PM-2:00AM")
                )
                .openPeriods(OpenPeriodsFixture.get())
                .build();
    }
}
