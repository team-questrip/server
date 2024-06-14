package com.questrip.reward.domain.place;

import com.questrip.reward.client.GooglePlaceClient;
import com.questrip.reward.fixture.PlaceFixture;
import com.questrip.reward.support.error.GlobalException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
class PlaceSearcherTest {

    @Autowired
    private PlaceSearcher placeSearcher;

    @MockBean
    private GooglePlaceClient googlePlaceClient;

    @DisplayName("구글 place 찾기를 실패할 경우 3번 반복 시행한다.")
    @Test
    void retry() {
        // given
        given(googlePlaceClient.placeDetails(any(), any(), any(), any()))
                .willThrow(RuntimeException.class);

        // when
        assertThatThrownBy(() -> placeSearcher.searchPlace("1234"))
                .isInstanceOf(GlobalException.class)
                .hasMessageContaining("외부 API 서버 오류");

        // then
        verify(googlePlaceClient, times(3)).placeDetails(any(), any(), any(), any());
    }
}