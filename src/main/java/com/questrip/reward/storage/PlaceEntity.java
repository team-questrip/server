package com.questrip.reward.storage;

import com.questrip.reward.domain.place.LatLng;
import com.questrip.reward.domain.place.OpenPeriods;
import com.questrip.reward.domain.place.Period;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.utils.GeometryUtils;
import com.questrip.reward.utils.PeriodConverter;
import com.questrip.reward.utils.StringListConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.locationtech.jts.geom.Point;

import java.util.List;

@Entity
@Table(name = "place")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE place SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class PlaceEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String googlePlaceId;
    private String placeName;
    private String primaryType;
    private String formattedAddress;
    @Column(columnDefinition = "POINT SRID 4326", nullable = false)
    private Point location;
    @Convert(converter = StringListConverter.class)
    private List<String> openingHours;
    @Convert(converter = PeriodConverter.class)
    @Column(columnDefinition = "longtext")
    private List<Period> openPeriods;
    private Boolean isDeleted;

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
                .location(GeometryUtils.getPoint(place.getLocation().getLongitude(), place.getLocation().getLatitude()))
                .openingHours(place.getOpeningHours())
                .openPeriods(place.getOpenPeriods().getPeriods())
                .isDeleted(false)
                .build();
    }

    @Builder
    private PlaceEntity(Long id, String googlePlaceId, String placeName, String primaryType, String formattedAddress, Point location, List<String> openingHours, List<Period> openPeriods, Boolean isDeleted) {
        this.id = id;
        this.googlePlaceId = googlePlaceId;
        this.placeName = placeName;
        this.primaryType = primaryType;
        this.formattedAddress = formattedAddress;
        this.location = location;
        this.openingHours = openingHours;
        this.openPeriods = openPeriods;
        this.isDeleted = isDeleted;
    }
}
