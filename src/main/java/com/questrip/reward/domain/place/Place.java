package com.questrip.reward.domain.place;

import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Stream;

@Getter
public class Place {
    private static final double CHECK_IN_SUCCESS_DISTANCE = 0.5;

    private String id;
    private String googlePlaceId;
    private String placeName;
    private String romanizedPlaceName;
    private String primaryType;
    private String formattedAddress;
    private LatLng location;
    private PlaceContent content;
    private List<String> openingHours;
    private OpenPeriods openPeriods;
    private List<PlaceImage> images;
    private Set<MenuGroup> menuGroups;

    public double calculateDistance(LatLng userLocation) {
        double lat = Math.toRadians(userLocation.getLatitude());
        double lon = Math.toRadians(userLocation.getLongitude());
        double placeLat = Math.toRadians(location.getLatitude());
        double placeLon = Math.toRadians(location.getLongitude());

        double earthRadius = 6371;
        return earthRadius * Math.acos(Math.sin(lat) * Math.sin(placeLat) + Math.cos(lat) * Math.cos(placeLat) * Math.cos(lon - placeLon));
    }

    public void checkIn(LatLng userLocation) {
        double distance = calculateDistance(userLocation);
        if(distance > CHECK_IN_SUCCESS_DISTANCE) {
            throw new GlobalException(ErrorCode.DISTANCE_CHECK_FAILED, "User is %.2f kilometers away from the location, which is greater than allowed distance (%.2f kilometers).".formatted(distance, CHECK_IN_SUCCESS_DISTANCE));
        }
    }

    @Builder
    private Place(String id, String googlePlaceId, String placeName, String romanizedPlaceName, String primaryType, String formattedAddress, LatLng location, PlaceContent content, List<String> openingHours, List<Period> openPeriods, List<PlaceImage> images, Set<MenuGroup> menuGroups) {
        this.id = id;
        this.googlePlaceId = googlePlaceId;
        this.placeName = placeName;
        this.romanizedPlaceName = romanizedPlaceName;
        this.primaryType = primaryType;
        this.formattedAddress = formattedAddress;
        this.content = content;
        this.location = location;
        this.openingHours = openingHours;
        this.openPeriods = new OpenPeriods(openPeriods);
        this.images = images;
        this.menuGroups = menuGroups;
    }

    public void addMenuGroup(MenuGroup group) {
        if (ObjectUtils.isEmpty(menuGroups)) {
            menuGroups = new HashSet<>();
        }

        findMenuGroup(group.getGroupName()).ifPresentOrElse(
                findGroup -> findGroup.addMenu(group.getMenus()),
                () -> menuGroups.add(group)
        );
    }

    public void addMenu(String groupName, Menu menu) {
        if (ObjectUtils.isEmpty(menuGroups)) {
            menuGroups = new HashSet<>();
        }

        findMenuGroup(groupName)
                .ifPresentOrElse(
                        findGroup -> findGroup.addMenu(menu),
                        () -> menuGroups.add(new MenuGroup("groupName", Set.of(menu)))
                );
    }

    private Optional<MenuGroup> findMenuGroup(String groupName) {
        return menuGroups.stream()
                .filter(g -> g.getGroupName().equals(groupName))
                .findFirst();
    }

    public Set<MenuGroup> getMenuGroups() {
        if (ObjectUtils.isEmpty(menuGroups)) {
            return new HashSet<>();
        }

        return menuGroups;
    }
}
