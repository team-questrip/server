package com.questrip.reward.client.response;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.questrip.reward.domain.place.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GooglePlaceSearchResponse {
    private String id;
    private LocalizedText displayName;
    private String primaryType;
    private String nationalPhoneNumber;
    private String internationalPhoneNumber;
    private String formattedAddress;
    private LatLng location;
    @JsonSetter(nulls = Nulls.SKIP)
    private OpeningHours regularOpeningHours = new OpeningHours(new ArrayList<>(), new ArrayList<>());

    @Getter
    public static class LocalizedText {
        private String text;
        private String languageCode;
    }

    @Getter
    @NoArgsConstructor
    public static class OpeningHours {
        private List<String> weekdayDescriptions;
        private List<Period> periods;

        public OpeningHours(List<String> weekdayDescriptions, List<Period> periods) {
            this.weekdayDescriptions = weekdayDescriptions;
            this.periods = periods;
        }
    }

    public Place toPlace(PlaceContent content, List<PlaceImage> images) {
        return Place.builder()
                .googlePlaceId(id)
                .placeName(displayName.getText())
                .primaryType(parseType(primaryType))
                .formattedAddress(formattedAddress)
                .location(location)
                .content(content)
                .images(images)
                .openingHours(regularOpeningHours.getWeekdayDescriptions())
                .openPeriods(regularOpeningHours.getPeriods())
                .build();
    }

    private String parseType(String type) {
        return Arrays.stream(type.split("_"))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining(" "));
    }
}
