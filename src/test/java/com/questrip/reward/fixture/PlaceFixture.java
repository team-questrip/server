package com.questrip.reward.fixture;

import com.questrip.reward.domain.place.LatLng;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.place.PlaceContent;
import com.questrip.reward.domain.place.PlaceImage;

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
                .content(getContent())
                .images(getImages())
                .build();
    }

    public static Place get(String id) {
        return Place.builder()
                .id(id)
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
                .content(getContent())
                .images(getImages())
                .build();
    }

    public static Place get(LatLng location) {
        return Place.builder()
                .googlePlaceId("placeId")
                .placeName("test")
                .primaryType("test")
                .formattedAddress("test")
                .location(location)
                .openingHours(List.of("Monday: 5:00PM-2:00AM",
                        "Tuesday: 5:00PM-2:00AM",
                        "Wednesday: 5:00PM-2:00AM",
                        "Thursday: 5:00PM-2:00AM",
                        "Friday: 5:00PM-2:00AM",
                        "Saturday: 5:00PM-2:00AM",
                        "Sunday: 5:00PM-2:00AM")
                )
                .openPeriods(OpenPeriodsFixture.get())
                .content(getContent())
                .images(getImages())
                .build();
    }

    public static PlaceContent getContent() {
        return new PlaceContent("testRecommend", "testActivity");
    }

    public static List<PlaceImage> getImages() {
        PlaceImage img1 = new PlaceImage(1, "test.test");
        PlaceImage img2 = new PlaceImage(2, "test.test");
        PlaceImage img3 = new PlaceImage(3, "test.test");

        return List.of(img1, img2, img3);
    }
}
