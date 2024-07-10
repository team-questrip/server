package com.questrip.reward.domain.recommend;

import com.questrip.reward.domain.place.LatLng;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.fixture.PlaceFixture;
import com.questrip.reward.storage.mongo.PlaceEntity;
import com.questrip.reward.storage.mongo.PlaceMongoRepository;
import com.questrip.reward.storage.mysql.RecommendEntity;
import com.questrip.reward.storage.mysql.RecommendJpaRepository;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import com.questrip.reward.support.response.SliceResult;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class RecommendFinderTest {

    @Autowired
    RecommendFinder recommendFinder;

    @Autowired
    RecommendJpaRepository recommendJpaRepository;

    @Autowired
    PlaceMongoRepository placeMongoRepository;

    public List<String> placeIds;

    @BeforeEach
    void setUp() {
        Place place1 = PlaceFixture.get();
        Place place2 = PlaceFixture.get();
        Place place3 = PlaceFixture.get();
        Place place4 = PlaceFixture.get();

        List<PlaceEntity> entities = List.of(place1, place2, place3, place4)
                .stream()
                .map(PlaceEntity::from)
                .collect(Collectors.toList());

        List<PlaceEntity> places = placeMongoRepository.saveAll(entities);
        placeIds = places.stream().map(PlaceEntity::getId).toList();
    }

    @AfterEach
    void tearDown() {
        recommendJpaRepository.deleteAllInBatch();
        placeMongoRepository.deleteAll();
    }

    @DisplayName("거절한 기록이 있는 장소는 추천하지 않는다.")
    @Test
    void getRecommends() {
        // given
        Long userId = 1L;
        Recommend r1 = createRecommend(placeIds.get(0), userId, Recommend.Status.DENIED);
        Recommend r2 = createRecommend(placeIds.get(1), userId, Recommend.Status.DENIED);

        List<RecommendEntity> entities = List.of(r1, r2)
                .stream()
                .map(RecommendEntity::from)
                .collect(Collectors.toList());

        recommendJpaRepository.saveAll(entities);

        LocalDateTime mockTime = LocalDateTime.of(2024, 6, 2, 0, 0, 0);
        MockedStatic<LocalDateTime> mock = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS);
        mock.when(LocalDateTime::now).thenReturn(mockTime);

        // when
        SliceResult<Place> recommendPlaces = recommendFinder.getRecommends(userId, new LatLng(37.5051148, 127.08420179999999),0, 10, "EN");
        List<String> result = recommendPlaces.getContent().stream().map(Place::getId).collect(Collectors.toList());

        // then
        assertThat(recommendPlaces.getContent().size()).isEqualTo(2);
        assertThat(result)
                .contains(placeIds.get(2), placeIds.get(3));
    }

    @DisplayName("당일 수락, 보관 이력이 있는 장소는 추천하지 않는다.")
    @Test
    void getRecommends2() {
        // given
        Long userId = 1L;
        Recommend r1 = createRecommend(placeIds.get(0), userId, Recommend.Status.DENIED);
        Recommend r2 = createRecommend(placeIds.get(1), userId, Recommend.Status.DENIED);
        Recommend r3 = createRecommend(placeIds.get(2), userId, Recommend.Status.ACCEPTED);
        Recommend r4 = createRecommend(placeIds.get(3), userId, Recommend.Status.KEPT);

        List<RecommendEntity> entities = List.of(r1, r2, r3, r4)
                .stream()
                .map(RecommendEntity::from)
                .collect(Collectors.toList());

        recommendJpaRepository.saveAll(entities);

        // when
        SliceResult<Place> recommendPlaces = recommendFinder.getRecommends(userId, new LatLng(37.5051148, 127.08420179999999), 0, 10, "EN");

        // then
        assertThat(recommendPlaces.getContent().size()).isZero();
    }

    @DisplayName("keep한 추천내역을 가져온다.")
    @Test
    void getKeptRecommends() {
        // given
        Long userId = 1L;
        Recommend r1 = createRecommend(placeIds.get(0), userId, Recommend.Status.DENIED);
        Recommend r2 = createRecommend(placeIds.get(1), userId, Recommend.Status.KEPT);
        Recommend r3 = createRecommend(placeIds.get(2), userId, Recommend.Status.ACCEPTED);
        Recommend r4 = createRecommend(placeIds.get(3), userId, Recommend.Status.KEPT);

        List<RecommendEntity> entities = List.of(r1, r2, r3, r4)
                .stream()
                .map(RecommendEntity::from)
                .collect(Collectors.toList());

        recommendJpaRepository.saveAll(entities);

        // when
        SliceResult<Recommend> keptRecommends = recommendFinder.getRecommendsWithStatus(userId, List.of(Recommend.Status.KEPT), 0, 10, "EN");

        // then
        assertThat(keptRecommends.getNumberOfElements()).isEqualTo(2);
    }

    @DisplayName("keep한 추천내역을 가져올 때 recommend의 place는 null이 아니다.")
    @Test
    void getKeptRecommends2() {
        // given
        Long userId = 1L;
        Recommend r1 = createRecommend(placeIds.get(0), userId, Recommend.Status.DENIED);
        Recommend r2 = createRecommend(placeIds.get(1), userId, Recommend.Status.KEPT);
        Recommend r3 = createRecommend(placeIds.get(2), userId, Recommend.Status.ACCEPTED);
        Recommend r4 = createRecommend(placeIds.get(3), userId, Recommend.Status.KEPT);

        List<RecommendEntity> entities = List.of(r1, r2, r3, r4)
                .stream()
                .map(RecommendEntity::from)
                .collect(Collectors.toList());

        recommendJpaRepository.saveAll(entities);

        // when
        SliceResult<Recommend> keptRecommends = recommendFinder.getRecommendsWithStatus(userId, List.of(Recommend.Status.KEPT), 0, 10, "EN");

        // then
        List<String> places = keptRecommends.getContent()
                .stream()
                .map(Recommend::getPlace)
                .map(Place::getId)
                .collect(Collectors.toList());

        assertThat(places)
                .containsExactlyInAnyOrder(
                        placeIds.get(1), placeIds.get(3)
                );
    }

    @DisplayName("Accept한 추천내역을 가져온다.")
    @Test
    void getAcceptedRecommends() {
        // given
        Long userId = 1L;
        Recommend r1 = createRecommend(placeIds.get(0), userId, Recommend.Status.DENIED);
        Recommend r2 = createRecommend(placeIds.get(1), userId, Recommend.Status.KEPT);
        Recommend r3 = createRecommend(placeIds.get(2), userId, Recommend.Status.ACCEPTED);
        Recommend r4 = createRecommend(placeIds.get(3), userId, Recommend.Status.KEPT);

        List<RecommendEntity> entities = List.of(r1, r2, r3, r4)
                .stream()
                .map(RecommendEntity::from)
                .collect(Collectors.toList());

        recommendJpaRepository.saveAll(entities);

        // when
        SliceResult<Recommend> keptRecommends = recommendFinder.getRecommendsWithStatus(userId, List.of(Recommend.Status.ACCEPTED), 0, 10, "EN");

        // then
        assertThat(keptRecommends.getNumberOfElements()).isEqualTo(1);
    }

    @DisplayName("Status에 맞는 추천내역을 가져온다.")
    @Test
    void getRecommendsInStatus() {
        // given
        Long userId = 1L;
        Recommend r1 = createRecommend(placeIds.get(0), userId, Recommend.Status.DENIED);
        Recommend r2 = createRecommend(placeIds.get(1), userId, Recommend.Status.KEPT);
        Recommend r3 = createRecommend(placeIds.get(2), userId, Recommend.Status.ACCEPTED);
        Recommend r4 = createRecommend(placeIds.get(3), userId, Recommend.Status.COMPLETED);

        List<RecommendEntity> entities = List.of(r1, r2, r3, r4)
                .stream()
                .map(RecommendEntity::from)
                .collect(Collectors.toList());

        recommendJpaRepository.saveAll(entities);

        // when
        SliceResult<Recommend> recommends = recommendFinder.getRecommendsWithStatus(userId, List.of(Recommend.Status.ACCEPTED, Recommend.Status.COMPLETED), 0, 10, "EN");

        // then
        assertThat(recommends.getNumberOfElements()).isEqualTo(2);
        assertThat(recommends.getContent()).extracting(
                "placeId", "status"
        )
                .containsExactlyInAnyOrder(
                        Tuple.tuple(placeIds.get(2), Recommend.Status.ACCEPTED),
                        Tuple.tuple(placeIds.get(3), Recommend.Status.COMPLETED)
                );
    }

    private Recommend createRecommend(String placeId, Long userId, Recommend.Status status) {
        return Recommend.builder()
                .place(PlaceFixture.get(placeId))
                .userId(userId)
                .status(status)
                .build();
    }

    @DisplayName("진행중인 recommend를 가져온다.")
    @Test
    void retrieveProgressRecommend() {
        // given
        Long userId = 1L;
        Recommend init = createRecommend(placeIds.get(0), userId, Recommend.Status.ACCEPTED);

        RecommendEntity saved = recommendJpaRepository.save(RecommendEntity.from(init));

        // when
        Recommend recommend = recommendFinder.retrieveProgressRecommend(1L);

        // then
        assertThat(recommend.getId()).isEqualTo(saved.getId());
        assertThat(recommend.getPlace().getId()).isEqualTo(placeIds.get(0));
    }

    @DisplayName("진행중인 recommend가 없을 경우 예외가 발생한다.")
    @Test
    void retrieveProgressRecommend2() {
        // given
        Long userId = 1L;

        // when & then
        assertThatThrownBy(() -> recommendFinder.retrieveProgressRecommend(1L))
                .isInstanceOf(GlobalException.class)
                .hasMessageContaining(ErrorCode.PROGRESS_RECOMMEND_NOT_FOUND.getMessage());
    }
}