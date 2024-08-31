package com.questrip.reward.domain.place;

import com.questrip.reward.fixture.PlaceFixture;
import com.questrip.reward.storage.mongo.PlaceEntity;
import com.questrip.reward.storage.mongo.PlaceMongoRepository;
import com.questrip.reward.support.response.SliceResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class PlaceFinderTest {

    @Autowired
    private PlaceFinder placeFinder;

    @Autowired
    private PlaceMongoRepository placeMongoRepository;

    @AfterEach
    void tearDown() {
        placeMongoRepository.deleteAll();
    }

    @DisplayName("id로 place를 찾는다.")
    @Test
    void findById() {
        // given
        PlaceEntity saved = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get()));

        // when
        Place find = placeFinder.findById(saved.getId());

        // then
        assertThat(find.getId()).isEqualTo(saved.getId());
    }

    @DisplayName("거리에서 가까운 순으로 정렬된 결과가 출력된다.")
    @Test
    void findAllNear() {
        // given
        LatLng 평화의공원 = new LatLng(37.5623116, 126.8951046);
        LatLng 홍대입구역 = new LatLng(37.5581082, 126.9259574);
        LatLng 난지한강공원 = new LatLng(37.5660148, 126.8765181);
        LatLng 롯데월드 = new LatLng(37.5111158, 127.098167);

        PlaceEntity p1 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(평화의공원)));
        PlaceEntity p2 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(홍대입구역)));
        PlaceEntity p3 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(난지한강공원)));
        PlaceEntity p4 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(롯데월드)));

        LatLng 월드컵경기장 = new LatLng(37.5682617, 126.8972735);

        // when
        SliceResult<Place> result = placeFinder.findAllNear("KO", null, 월드컵경기장, 0, 5);

        // then
        assertThat(result.getNumberOfElements()).isEqualTo(4);
        assertThat(result.getContent())
                .extracting("location")
                .containsExactly(
                        평화의공원, 난지한강공원, 홍대입구역, 롯데월드
                );
    }

    @DisplayName("거리에서 가까운 순으로 정렬된 결과가 출력된다.")
    @Test
    void findAllNear2() {
        // given
        LatLng 평화의공원 = new LatLng(37.5623116, 126.8951046);
        LatLng 홍대입구역 = new LatLng(37.5581082, 126.9259574);
        LatLng 난지한강공원 = new LatLng(37.5660148, 126.8765181);
        LatLng 롯데월드 = new LatLng(37.5111158, 127.098167);

        PlaceEntity p1 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(평화의공원)));
        PlaceEntity p2 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(홍대입구역)));
        PlaceEntity p3 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(난지한강공원)));
        PlaceEntity p4 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(롯데월드)));

        LatLng 월드컵경기장 = new LatLng(37.5682617, 126.8972735);

        // when
        SliceResult<Place> result = placeFinder.findAllNear("KO", null, 월드컵경기장, 0, 2);

        // then
        assertThat(result.getNumberOfElements()).isEqualTo(2);
        assertThat(result.getContent())
                .extracting("location")
                .containsExactly(
                        평화의공원, 난지한강공원
                );
    }

    @DisplayName("거리에서 가까운 순으로 정렬된 결과가 출력된다.")
    @Test
    void findAllNear3() {
        // given
        LatLng 평화의공원 = new LatLng(37.5623116, 126.8951046);
        LatLng 홍대입구역 = new LatLng(37.5581082, 126.9259574);
        LatLng 난지한강공원 = new LatLng(37.5660148, 126.8765181);
        LatLng 롯데월드 = new LatLng(37.5111158, 127.098167);

        PlaceEntity p1 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(평화의공원)));
        PlaceEntity p2 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(홍대입구역)));
        PlaceEntity p3 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(난지한강공원)));
        PlaceEntity p4 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(롯데월드)));

        LatLng 월드컵경기장 = new LatLng(37.5682617, 126.8972735);

        // when
        SliceResult<Place> result = placeFinder.findAllNear("KO", null, 월드컵경기장, 1, 2);

        // then
        assertThat(result.getNumberOfElements()).isEqualTo(2);
        assertThat(result.getContent())
                .extracting("location")
                .containsExactly(
                        홍대입구역, 롯데월드
                );
    }

    @DisplayName("해당 카테고리의 그룹에 속하는 장소를 찾을 수 있다.")
    @Test
    void findAllNear4() {
        // given
        PlaceEntity p1 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(Category.RESTAURANT)));
        PlaceEntity p2 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(Category.RESTAURANT)));
        PlaceEntity p3 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(Category.DAY_TOUR)));
        PlaceEntity p4 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(Category.RESTAURANT)));

        LatLng 월드컵경기장 = new LatLng(37.5682617, 126.8972735);

        // when
        SliceResult<Place> result = placeFinder.findAllNear("KO", CategoryGroup.FOOD_AND_DRINKS, 월드컵경기장, 0, 10);

        // then
        assertThat(result.getNumberOfElements()).isEqualTo(3);
        assertThat(result.getContent())
                .extracting("category")
                .containsExactly(
                        Category.RESTAURANT, Category.RESTAURANT, Category.RESTAURANT
                );
    }

    @DisplayName("조회 조건에 카테고리가 없을 경우 모든 장소를 가까운 순으로 조회한다.")
    @Test
    void findAllNear5() {
        // given
        PlaceEntity p1 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(Category.RESTAURANT)));
        PlaceEntity p2 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(Category.RESTAURANT)));
        PlaceEntity p3 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(Category.BAR)));
        PlaceEntity p4 = placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get(Category.RESTAURANT)));

        LatLng 월드컵경기장 = new LatLng(37.5682617, 126.8972735);

        // when
        SliceResult<Place> result = placeFinder.findAllNear("KO", null, 월드컵경기장, 0, 10);

        // then
        assertThat(result.getNumberOfElements()).isEqualTo(4);
        assertThat(result.getContent())
                .extracting("category")
                .containsExactlyInAnyOrder(
                        Category.RESTAURANT, Category.RESTAURANT, Category.BAR, Category.RESTAURANT
                );
    }

    @DisplayName("place id 리스트에 있는 장소는 제외하고 추천한다.")
    @Test
    void findRecommendPlace() {
        // given
        for(int i=0; i<10; i++) {
            placeMongoRepository.save(PlaceEntity.from(PlaceFixture.get()));
        }
        List<String> placeIds = placeMongoRepository.findAll()
                .stream()
                .map(PlaceEntity::getId)
                .collect(Collectors.toList());

        LatLng location = new LatLng(37.5581082, 126.9259574);

        // when
        SliceResult<Place> recommendPlace = placeFinder.findRecommendPlace(location, List.of(placeIds.get(0), placeIds.get(1)), 0, 10, "EN");

        // then
        assertThat(recommendPlace.getContent().size()).isEqualTo(8);
    }
}