package com.questrip.reward.domain.place;

import com.questrip.reward.fixture.PlaceFixture;
import com.questrip.reward.storage.PlaceEntity;
import com.questrip.reward.storage.PlaceMongoRepository;
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
}