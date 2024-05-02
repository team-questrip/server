package com.questrip.reward.domain.place;

import com.questrip.reward.fixture.PlaceFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PlaceSearcherTest {

    @Autowired
    private PlaceSearcher placeSearcher;

    @DisplayName("구글 장소 찾기 결과를 반환한다.")
    @Test
    void searchPlace() {
        // given
        String 토속촌 = "ChIJb5OOGL6ifDURU29ID3t8aOA";
        PlaceContent content = PlaceFixture.getContent();
        List<PlaceImage> images = PlaceFixture.getImages();

        // when
        Place place = placeSearcher.searchPlace(토속촌).toPlace(content, images);

        // then
        assertThat(place)
                .extracting(
                        "googlePlaceId",
                        "placeName",
                        "primaryType",
                        "formattedAddress",
                        "location"
                )
                .containsExactly(
                        토속촌,
                        "Tosokchon Samgyetang",
                        "korean_restaurant",
                        "5 Jahamun-ro 5-gil, Jongno-gu, Seoul, South Korea",
                        new LatLng(37.577778599999995, 126.9715909)
                );
        assertThat(place.getContent())
                .extracting(
                        "recommendationReason",
                        "activity"
                )
                .containsExactly(
                        content.getRecommendationReason(),
                        content.getActivity()
                );
    }
}