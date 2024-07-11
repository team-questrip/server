package com.questrip.reward.fixture;

import com.questrip.reward.domain.place.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlaceFixture {
    public static Place get() {
        return Place.builder()
                .googlePlaceId("ChIJo0gMXbOlfDURSjmLcTy52qQ")
                .placeName("꼬치집")
                .romanizedPlaceName("Kkochijib")
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
                .menuGroups(getMenuGroups2())
                .build();
    }

    public static Place get(String id) {
        return Place.builder()
                .id(id)
                .googlePlaceId("ChIJo0gMXbOlfDURSjmLcTy52qQ")
                .placeName("꼬치집")
                .romanizedPlaceName("Kkochijib")
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
                .menuGroups(getMenuGroups())
                .build();
    }

    public static Place get(LatLng location) {
        return Place.builder()
                .googlePlaceId("ChIJo0gMXbOlfDURSjmLcTy52qQ")
                .placeName("꼬치집")
                .romanizedPlaceName("Kkochijib")
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
                .menuGroups(getMenuGroups())
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

    public static Set<MenuGroup> getMenuGroups() {
        Menu 볶음밥 = new Menu("볶음밥","bbokeumbap", 8000, "고슬고슬 맛있는 볶음밥");
        Menu 짜장밥 = new Menu("짜장밥", "jjajangbap", 9000, "짜장소스 추가");
        MenuGroup menuGroup = new MenuGroup("밥류", Set.of(볶음밥, 짜장밥));

        Menu 짬뽕 = new Menu("짬뽕", "jjambbong",10000, "얼큰 짬뽕");
        Menu 짜장면 = new Menu("짜장면", "jjajangmyeon", 7000, "자신없어요");
        MenuGroup menuGroup2 = new MenuGroup("면류", Set.of(짬뽕, 짜장면));

        HashSet<MenuGroup> hashSet = new HashSet<>();
        hashSet.addAll(List.of(menuGroup, menuGroup2));
        return hashSet;
    }

    public static Set<MenuGroup> getMenuGroups2() {
        Menu 볶음밥 = new Menu("집밥","Jibbap", 8000, "Stew made with soft tofu, seafood, and eggs.");
        Menu 짜장밥 = new Menu("김치찌개","Kimchi Jjigae", 9000, "Rich and deep-flavored stew made with fermented soybeans.");
        MenuGroup menuGroup = new MenuGroup("Meals", Set.of(볶음밥, 짜장밥));

        Menu 짬뽕 = new Menu("콩국수", "Soybean Noodles", 10000, "Noodles in finely ground soybean soup, a summer delicacy.");
        Menu 짜장면 = new Menu("냉면", "Cold Noodles", 7000, "Buckwheat noodles in cold broth, a summer specialty.");
        MenuGroup menuGroup2 = new MenuGroup("Seasonal Menu", Set.of(짬뽕, 짜장면));

        HashSet<MenuGroup> hashSet = new HashSet<>();
        hashSet.addAll(List.of(menuGroup, menuGroup2));
        return hashSet;
    }
}
