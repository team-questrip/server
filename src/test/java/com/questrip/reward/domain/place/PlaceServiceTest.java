package com.questrip.reward.domain.place;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PlaceServiceTest {

    @Autowired
    PlaceService placeService;

    @DisplayName("역지오코딩 결과를 반환한다.")
    @Test
    void reverseGeocode() {
        // given
        LatLng 잠실동 = new LatLng(37.5068006, 127.0830179);

        // when
        String address = placeService.reverseGeocode(잠실동);

        // then
        assertThat(address).isEqualTo("226-19 Jamsil-dong, Songpa District, Seoul, South Korea");
    }
}