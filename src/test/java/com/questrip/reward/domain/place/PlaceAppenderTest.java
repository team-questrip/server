package com.questrip.reward.domain.place;

import com.questrip.reward.fixture.PlaceFixture;
import com.questrip.reward.storage.PlaceEntity;
import com.questrip.reward.storage.PlaceJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class PlaceAppenderTest {

    @Autowired
    private PlaceAppender placeAppender;

    @Autowired
    private PlaceJpaRepository placeJpaRepository;

    @AfterEach
    void tearDown() {
        placeJpaRepository.deleteAllInBatch();
    }

    @DisplayName("Place를 저장한다.")
    @Test
    void append() {
        // given
        Place place = PlaceFixture.get();

        // when
        Place saved = placeAppender.append(place);

        // then
        List<PlaceEntity> entities = placeJpaRepository.findAll();
        assertThat(entities.size()).isOne();

        assertThat(saved.getId()).isNotNull();
        assertThat(saved).extracting(
                "googlePlaceId",
                "placeName",
                "primaryType",
                "formattedAddress",
                "location",
                "openingHours",
                "openPeriods"
        ).containsExactly(
                place.getGooglePlaceId(),
                place.getPlaceName(),
                place.getPrimaryType(),
                place.getFormattedAddress(),
                place.getLocation(),
                place.getOpeningHours(),
                place.getOpenPeriods()
        );
    }
}