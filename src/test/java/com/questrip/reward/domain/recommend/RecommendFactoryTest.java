package com.questrip.reward.domain.recommend;

import com.questrip.reward.domain.place.Place;
import com.questrip.reward.domain.place.PlaceFinder;
import com.questrip.reward.fixture.PlaceFixture;
import com.questrip.reward.storage.mysql.RecommendEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
class RecommendFactoryTest {

    @Autowired
    RecommendFactory recommendFactory;

    @MockBean
    PlaceFinder placeFinder;

    @BeforeEach
    void setUp() {
        given(placeFinder.findById(any()))
                .willReturn(PlaceFixture.get("test"));
    }

    @DisplayName("")
    @Test
    void of() {
        // given
        RecommendEntity entity = RecommendEntity.builder()
                .id(1L)
                .placeId("test")
                .status(Recommend.Status.KEPT)
                .build();

        // when
        Recommend recommend = recommendFactory.from(entity.toRecommend());

        // then
        assertThat(recommend.getPlace().getId()).isEqualTo(entity.getPlaceId());
    }
}