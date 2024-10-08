package com.questrip.reward.storage.mongo;

import com.questrip.reward.domain.place.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "place")
public class PlaceEntity extends BaseEntity {

    @Id
    private String id;
    private CategoryGroup categoryGroup;
    private Category category;
    private String googlePlaceId;
    private String placeName;
    private String romanizedPlaceName;
    private String primaryType;
    private String formattedAddress;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Point location;
    private PlaceContent content;
    private List<String> openingHours;
    private List<Period> openPeriods;
    private List<PlaceImage> images;
    private Set<MenuGroup> menuGroups = new HashSet<>();
    private Map<String, TranslatedInfo> translations = new HashMap<>();

    public Place toPlace() {
        return Place.builder()
                .id(id)
                .category(category)
                .translatedCategoryGroup(category.getCategoryGroup().getTranslation("EN"))
                .translatedCategory(category.getTranslation("EN"))
                .googlePlaceId(googlePlaceId)
                .placeName(placeName)
                .romanizedPlaceName(romanizedPlaceName)
                .primaryType(parseType(primaryType))
                .formattedAddress(formattedAddress)
                .location(new LatLng(location.getY(), location.getX()))
                .content(content)
                .openingHours(openingHours)
                .openPeriods(openPeriods)
                .images(images)
                .menuGroups(menuGroups)
                .build();
    }

    public Place toPlace(String language) {
        TranslatedInfo info = translations.getOrDefault(language, translations.get("EN"));
        if(info == null) {
            info = TranslatedInfo.builder()
                    .content(new PlaceContent("null", "null"))
                    .formattedAddress("null")
                    .primaryType("null")
                    .openingHours(List.of("null"))
                    .build();
        }

        return Place.builder()
                .id(id)
                .category(category)
                .translatedCategory(category.getTranslation(language))
                .translatedCategoryGroup(categoryGroup.getTranslation(language))
                .googlePlaceId(googlePlaceId)
                .placeName(placeName)
                .romanizedPlaceName(romanizedPlaceName)
                .primaryType(parseType(info.getPrimaryType()))
                .formattedAddress(info.getFormattedAddress())
                .location(new LatLng(location.getY(), location.getX()))
                .content(info.getContent())
                .openingHours(info.getOpeningHours())
                .openPeriods(openPeriods)
                .images(images)
                .menuGroups(info.getMenuGroups())
                .build();
    }

    public static PlaceEntity from(Place place) {
        return PlaceEntity.builder()
                .id(place.getId())
                .categoryGroup(CategoryGroup.findGroup(place.getCategory()))
                .category(place.getCategory())
                .googlePlaceId(place.getGooglePlaceId())
                .placeName(place.getPlaceName())
                .romanizedPlaceName(place.getRomanizedPlaceName())
                .primaryType(place.getPrimaryType())
                .formattedAddress(place.getFormattedAddress())
                .location(new Point(place.getLocation().getLongitude(), place.getLocation().getLatitude()))
                .content(place.getContent())
                .openingHours(place.getOpeningHours())
                .openPeriods(place.getOpenPeriods().getPeriods())
                .images(place.getImages())
                .menuGroups(place.getMenuGroups())
                .build();
    }

    public void addTranslation(Map<String, TranslatedInfo> translations) {
        this.translations = translations;
    }

    public void update(Place place) {
        this.categoryGroup = CategoryGroup.findGroup(place.getCategory());
        this.category = place.getCategory();
        this.googlePlaceId = place.getGooglePlaceId();
        this.placeName = place.getPlaceName();
        this.primaryType = place.getPrimaryType();
        this.formattedAddress = place.getFormattedAddress();
        this.location = new Point(place.getLocation().getLongitude(), place.getLocation().getLatitude());
        this.content = place.getContent();
        this.openingHours = place.getOpeningHours();
        this.openPeriods = place.getOpenPeriods().getPeriods();
        this.images = place.getImages();
        this.menuGroups = place.getMenuGroups();
    }

    @Builder
    private PlaceEntity(String id, CategoryGroup categoryGroup, Category category, String googlePlaceId, String placeName, String romanizedPlaceName, String primaryType, String formattedAddress, Point location, PlaceContent content, List<String> openingHours, List<Period> openPeriods, List<PlaceImage> images, Set<MenuGroup> menuGroups) {
        this.id = id;
        this.categoryGroup = categoryGroup;
        this.category = category;
        this.googlePlaceId = googlePlaceId;
        this.placeName = placeName;
        this.romanizedPlaceName = romanizedPlaceName;
        this.primaryType = primaryType;
        this.formattedAddress = formattedAddress;
        this.location = location;
        this.content = content;
        this.openingHours = openingHours;
        this.openPeriods = openPeriods;
        this.images = images;
        this.menuGroups = menuGroups;
    }

    private String parseType(String type) {
        return Arrays.stream(type.split("_"))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining(" "));
    }
}
