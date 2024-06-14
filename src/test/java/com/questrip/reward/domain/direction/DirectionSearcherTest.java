package com.questrip.reward.domain.direction;

import com.questrip.reward.client.GoogleDirectionClient;
import com.questrip.reward.domain.place.LatLng;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
class DirectionSearcherTest {

    @Autowired
    private DirectionSearcher directionSearcher;

    @MockBean
    private GoogleDirectionClient googleDirectionClient;

    @DisplayName("API 호출 중 에러가 발생했을 경우 UNKNOWN으로 보인다.")
    @Test
    void getSummaryWithException() {
        // given
        String 꼬치집 = "ChIJo0gMXbOlfDURSjmLcTy52qQ";
        LatLng userLocation = new LatLng(37.5912474, 126.9184582);
        given(googleDirectionClient.getDirection(any(), any(), any(), any()))
                .willThrow(new RuntimeException("API 호출 실패"));

        // when
        DirectionSummary summary = directionSearcher.getSummary(userLocation, 꼬치집);

        // then
        assertThat(summary.getDistance()).isEqualTo("UNKNOWN");
        assertThat(summary.getDuration()).isEqualTo("UNKNOWN");
    }

    @DisplayName("API 호출 중 에러가 발생했을 경우 3회 재시도 한다.")
    @Test
    void getSummaryRetry() {
        // given
        String 꼬치집 = "ChIJo0gMXbOlfDURSjmLcTy52qQ";
        LatLng userLocation = new LatLng(37.5912474, 126.9184582);
        given(googleDirectionClient.getDirection(any(), any(), any(), any()))
                .willThrow(new RuntimeException("API 호출 실패"));

        // when
        DirectionSummary summary = directionSearcher.getSummary(userLocation, 꼬치집);

        // then
        verify(googleDirectionClient, times(3)).getDirection(any(), any(), any(), any());
    }
}