package com.questrip.reward.domain.recommend;

import com.questrip.reward.fixture.PlaceFixture;
import com.questrip.reward.storage.mongo.PlaceEntity;
import com.questrip.reward.storage.mongo.PlaceMongoRepository;
import com.questrip.reward.storage.mysql.RecommendEntity;
import com.questrip.reward.storage.mysql.RecommendJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class RecommendServiceTest {

    @Autowired
    RecommendService recommendService;

    @Autowired
    PlaceMongoRepository placeMongoRepository;

    @Autowired
    RecommendJpaRepository recommendRepository;

    @AfterEach
    void tearDown() {
        placeMongoRepository.deleteAll();
        recommendRepository.deleteAllInBatch();
    }

    @DisplayName("추천 내용을 저장한다.")
    @Test
    void save() {
        // given
        PlaceEntity place = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get()));

        // when
        Recommend recommend = recommendService.save(1L, place.getId(), Recommend.Status.KEPT);

        // then
        assertThat(recommend.getId()).isNotNull();
        assertThat(recommend.getPlace().getId()).isEqualTo(place.getId());
        assertThat(recommend)
                .extracting(
                        "userId", "status"
                )
                .containsExactly(
                        1L, Recommend.Status.KEPT
                );
        List<RecommendEntity> recommends = recommendRepository.findAll();
        assertThat(recommends.size()).isEqualTo(1);
    }
}