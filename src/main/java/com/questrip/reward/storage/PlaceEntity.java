package com.questrip.reward.storage;

import com.questrip.reward.domain.place.LatLng;
import com.questrip.reward.domain.place.Period;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.utils.PeriodConverter;
import com.questrip.reward.utils.StringListConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "place")
public class PlaceEntity extends BaseEntity {

    @Id
    private String id;
    private String googlePlaceId;
    private String placeName;
    private String primaryType;
    private String formattedAddress;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Point location;
    @Convert(converter = StringListConverter.class)
    private List<String> openingHours;
    @Convert(converter = PeriodConverter.class)
    private List<Period> openPeriods;

    public Place toPlace() {
        return Place.builder()
                .id(id)
                .googlePlaceId(googlePlaceId)
                .placeName(placeName)
                .primaryType(primaryType)
                .formattedAddress(formattedAddress)
                .location(new LatLng(location.getY(), location.getX()))
                .openingHours(openingHours)
                .openPeriods(openPeriods)
                .build();
    }

    public static PlaceEntity from(Place place) {
        return PlaceEntity.builder()
                .id(place.getId())
                .googlePlaceId(place.getGooglePlaceId())
                .placeName(place.getPlaceName())
                .primaryType(place.getPrimaryType())
                .formattedAddress(place.getFormattedAddress())
                .location(new Point(place.getLocation().getLongitude(), place.getLocation().getLatitude()))
                .openingHours(place.getOpeningHours())
                .openPeriods(place.getOpenPeriods().getPeriods())
                .build();
    }

    @Builder
    private PlaceEntity(String id, String googlePlaceId, String placeName, String primaryType, String formattedAddress, Point location, List<String> openingHours, List<Period> openPeriods) {
        this.id = id;
        this.googlePlaceId = googlePlaceId;
        this.placeName = placeName;
        this.primaryType = primaryType;
        this.formattedAddress = formattedAddress;
        this.location = location;
        this.openingHours = openingHours;
        this.openPeriods = openPeriods;
    }
}
