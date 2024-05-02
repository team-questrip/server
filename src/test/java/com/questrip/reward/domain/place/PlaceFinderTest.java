package com.questrip.reward.domain.place;

import com.questrip.reward.fixture.PlaceFixture;
import com.questrip.reward.storage.PlaceEntity;
import com.questrip.reward.storage.PlaceMongoRepository;
import com.questrip.reward.support.response.SliceResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        SliceResult<Place> result = placeFinder.findAllNear(월드컵경기장, 0, 5);

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
        SliceResult<Place> result = placeFinder.findAllNear(월드컵경기장, 0, 2);

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
        SliceResult<Place> result = placeFinder.findAllNear(월드컵경기장, 1, 2);

        // then
        assertThat(result.getNumberOfElements()).isEqualTo(2);
        assertThat(result.getContent())
                .extracting("location")
                .containsExactly(
                        홍대입구역, 롯데월드
                );
    }
}