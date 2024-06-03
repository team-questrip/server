package com.questrip.reward.domain.recommend;

import com.questrip.reward.fixture.PlaceFixture;
import com.questrip.reward.storage.mysql.RecommendEntity;
import com.questrip.reward.storage.mysql.RecommendJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class RecommendRepositoryTest {

    @Autowired
    RecommendRepository recommendRepository;

    @Autowired
    RecommendJpaRepository recommendJpaRepository;

    @AfterEach
    void tearDown() {
        recommendJpaRepository.deleteAllInBatch();
    }

    @DisplayName("거절된 추천 장소는 추천하지 않는다.")
    @Test
    void getExcludePlaceId() {
        // given
        Long userId = 1L;

        Recommend reco1 = createRecommend("test1", userId, Recommend.Status.DENIED);
        Recommend reco2 = createRecommend("test2", userId, Recommend.Status.DENIED);
        Recommend reco3 = createRecommend("test3", userId, Recommend.Status.ACCEPTED);
        Recommend reco4 = createRecommend("test4", userId, Recommend.Status.KEPT);

        List<RecommendEntity> recommends = List.of(reco1, reco2, reco3, reco4)
                .stream()
                .map(RecommendEntity::from)
                .collect(Collectors.toList());

        recommendJpaRepository.saveAll(recommends);

        LocalDateTime start = LocalDateTime.now().plusDays(1L);
        LocalDateTime end = LocalDateTime.now().plusDays(1L);

        // when
        List<String> placeIds = recommendRepository.getExcludePlaceIds(1L, start, end);

        // then
        assertThat(placeIds.size()).isEqualTo(2);
        assertThat(placeIds)
                .contains("test3", "test4");
    }

    @DisplayName("당일 수락, 저장된 추천 장소는 추천하지 않는다.")
    @Test
    void getExcludePlaceId2() {
        // given
        Long userId = 1L;

        Recommend reco1 = createRecommend("test1", userId, Recommend.Status.DENIED);
        Recommend reco2 = createRecommend("test2", userId, Recommend.Status.DENIED);
        Recommend reco3 = createRecommend("test3", userId, Recommend.Status.ACCEPTED);
        Recommend reco4 = createRecommend("test4", userId, Recommend.Status.KEPT);

        List<RecommendEntity> recommends = List.of(reco1, reco2, reco3, reco4)
                .stream()
                .map(RecommendEntity::from)
                .collect(Collectors.toList());

        recommendJpaRepository.saveAll(recommends);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 23, 59, 59);

        // when
        List<String> placeIds = recommendRepository.getExcludePlaceIds(1L, start, end);

        // then
        assertThat(placeIds.size()).isZero();
    }

    @DisplayName("")
    @Test
    void getExcludePlaceId3() {
        // given
        Long userId = 1L;
        Long userId2 = 2L;

        Recommend reco1 = createRecommend("test1", userId, Recommend.Status.DENIED);
        Recommend reco2 = createRecommend("test2", userId, Recommend.Status.DENIED);
        Recommend reco3 = createRecommend("test3", userId, Recommend.Status.ACCEPTED);
        Recommend reco4 = createRecommend("test4", userId, Recommend.Status.KEPT);
        Recommend reco5 = createRecommend("test5", userId2, Recommend.Status.ACCEPTED);
        Recommend reco6 = createRecommend("test6", userId2, Recommend.Status.KEPT);

        List<RecommendEntity> recommends = List.of(reco1, reco2, reco3, reco4, reco5, reco6)
                .stream()
                .map(RecommendEntity::from)
                .collect(Collectors.toList());

        recommendJpaRepository.saveAll(recommends);

        LocalDateTime start = LocalDateTime.now().plusDays(1L);
        LocalDateTime end = LocalDateTime.now().plusDays(1L);

        // when
        List<String> placeIds = recommendRepository.getExcludePlaceIds(1L, start, end);

        // then
        assertThat(placeIds.size()).isEqualTo(2);
        assertThat(placeIds).contains("test3", "test4");
    }

    private Recommend createRecommend(String placeId, Long userId, Recommend.Status status) {
        return Recommend.builder()
                .place(PlaceFixture.get(placeId))
                .userId(userId)
                .status(status)
                .build();
    }
}