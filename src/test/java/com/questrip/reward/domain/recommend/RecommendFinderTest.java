package com.questrip.reward.domain.recommend;

import com.questrip.reward.domain.place.LatLng;
import com.questrip.reward.domain.place.Place;
import com.questrip.reward.fixture.PlaceFixture;
import com.questrip.reward.storage.mongo.PlaceEntity;
import com.questrip.reward.storage.mongo.PlaceMongoRepository;
import com.questrip.reward.storage.mysql.RecommendEntity;
import com.questrip.reward.storage.mysql.RecommendJpaRepository;
import com.questrip.reward.support.response.SliceResult;
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
        List<Place> recommendPlaces = recommendFinder.getRecommends(userId, new LatLng(37.5051148, 127.08420179999999));
        List<String> result = recommendPlaces.stream().map(Place::getId).collect(Collectors.toList());

        // then
        assertThat(recommendPlaces.size()).isEqualTo(2);
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
        List<Place> recommendPlaces = recommendFinder.getRecommends(userId, new LatLng(37.5051148, 127.08420179999999));

        // then
        assertThat(recommendPlaces.size()).isZero();
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
        SliceResult<Recommend> keptRecommends = recommendFinder.getKeptRecommends(userId, 0, 10);

        // then
        assertThat(keptRecommends.getNumberOfElements()).isEqualTo(2);
    }

    @DisplayName("keep한 추천내역을 가져온다.")
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
        SliceResult<Recommend> keptRecommends = recommendFinder.getKeptRecommends(userId, 0, 10);

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

    private Recommend createRecommend(String placeId, Long userId, Recommend.Status status) {
        return Recommend.builder()
                .place(PlaceFixture.get(placeId))
                .userId(userId)
                .status(status)
                .build();
    }
}