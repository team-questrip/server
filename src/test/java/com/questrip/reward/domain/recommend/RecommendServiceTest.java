package com.questrip.reward.domain.recommend;

import com.questrip.reward.domain.place.LatLng;
import com.questrip.reward.fixture.PlaceFixture;
import com.questrip.reward.storage.mongo.PlaceEntity;
import com.questrip.reward.storage.mongo.PlaceMongoRepository;
import com.questrip.reward.storage.mysql.RecommendEntity;
import com.questrip.reward.storage.mysql.RecommendJpaRepository;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @DisplayName("진행중인 추천이 있을경우 예외가 발생한다.")
    @Test
    void save2() {
        // given
        PlaceEntity place = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get()));
        Recommend recommend = recommendService.save(1L, place.getId(), Recommend.Status.ACCEPTED);

        // when & then
        assertThatThrownBy(() -> recommendService.save(1L, place.getId(), Recommend.Status.ACCEPTED))
                .isInstanceOf(GlobalException.class)
                .hasMessageContaining(ErrorCode.ALREADY_EXIST_PROGRESS_RECOMMEND.getMessage());
    }

    @DisplayName("진행중인 추천이 있을경우 추천을 받을 수 없다.")
    @Test
    void getRecommendPlaces() {
        // given
        PlaceEntity place = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get()));
        Recommend recommend = recommendService.save(1L, place.getId(), Recommend.Status.ACCEPTED);

        // when & then
        assertThatThrownBy(() -> recommendService.getRecommendPlaces(1L, new LatLng(35.123, 123.23)))
                .isInstanceOf(GlobalException.class)
                .hasMessageContaining(ErrorCode.ALREADY_EXIST_PROGRESS_RECOMMEND.getMessage());
    }

    @DisplayName("Recommend 상태를 업데이트한다.")
    @Test
    void updateRecommendStatus() {
        // given
        Long userId = 1L;
        LatLng userLocation = new LatLng(35.123, 123.22);
        PlaceEntity place = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get()));
        recommendService.save(1L, place.getId(), Recommend.Status.ACCEPTED);

        // when
        Recommend recommend = recommendService.updateRecommendStatus(userId, userLocation, Recommend.Status.REVOKED);

        // then
        assertThat(recommend.getStatus()).isEqualTo(Recommend.Status.REVOKED);
    }

    @DisplayName("Check In 거리가 500m를 넘을 경우 상태가 업데이트되지 않는다.")
    @Test
    void updateRecommendStatus2() {
        // given
        Long userId = 1L;
        LatLng userLocation = new LatLng(35.123, 123.22);
        PlaceEntity place = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get()));
        recommendService.save(1L, place.getId(), Recommend.Status.ACCEPTED);

        // when & then
        assertThatThrownBy(() -> recommendService.updateRecommendStatus(userId, userLocation, Recommend.Status.COMPLETED))
                .isInstanceOf(GlobalException.class)
                        .hasMessageContaining(ErrorCode.DISTANCE_CHECK_FAILED.getMessage());

        Recommend recommend = recommendService.retrieveProgressRecommend(userId);

        assertThat(recommend.getStatus()).isEqualTo(Recommend.Status.ACCEPTED);
    }

    @DisplayName("Check In 거리가 500m 이내일 경우 체크인에 성공한다.")
    @Test
    void updateRecommendStatus3() {
        // given
        Long userId = 1L;
        LatLng userLocation = new LatLng(37.5051148, 127.08420179999999);
        PlaceEntity place = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get()));
        recommendService.save(1L, place.getId(), Recommend.Status.ACCEPTED);

        // when
        Recommend recommend = recommendService.updateRecommendStatus(userId, userLocation, Recommend.Status.COMPLETED);

        // then
        assertThat(recommend.getStatus()).isEqualTo(Recommend.Status.COMPLETED);
    }
}