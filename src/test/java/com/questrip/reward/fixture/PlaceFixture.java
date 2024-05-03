package com.questrip.reward.fixture;

import com.questrip.reward.domain.place.LatLng;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.place.PlaceContent;
import com.questrip.reward.domain.place.PlaceImage;

import java.util.List;

public class PlaceFixture {
    public static Place get() {
        return Place.builder()
                .googlePlaceId("ChIJo0gMXbOlfDURSjmLcTy52qQ")
                .placeName("Kkochijib")
                .primaryType("japanese_restaurant")
                .formattedAddress("South Korea, Seoul, Songpa-gu, 잠실본동 314-5")
                .location(new LatLng(37.5051148, 127.08420179999999))
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
                .googlePlaceId("ChIJo0gMXbOlfDURSjmLcTy52qQ")
                .placeName("Kkochijib")
                .primaryType("japanese_restaurant")
                .formattedAddress("South Korea, Seoul, Songpa-gu, 잠실본동 314-5")
                .location(new LatLng(37.5051148, 127.08420179999999))
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
                .googlePlaceId("ChIJo0gMXbOlfDURSjmLcTy52qQ")
                .placeName("Kkochijib")
                .primaryType("japanese_restaurant")
                .formattedAddress("South Korea, Seoul, Songpa-gu, 잠실본동 314-5")
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
        PlaceImage img1 = new PlaceImage(1, "https://questrip-reward.s3.ap-northeast-2.amazonaws.com/836d62f7-b1a7-4550-b137-f292ea5f881c.HEIC");
        PlaceImage img2 = new PlaceImage(2, "https://questrip-reward.s3.ap-northeast-2.amazonaws.com/e2f8b8cc-fd53-47da-bea5-c113f8f6393c.jpeg");

        return List.of(img1, img2);
    }
}
