package com.questrip.reward.domain.place;

import com.questrip.reward.fixture.PlaceFixture;
import com.questrip.reward.storage.mongo.PlaceEntity;
import com.questrip.reward.storage.mongo.PlaceMongoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PlaceUpdaterTest {

    @Autowired
    PlaceUpdater placeUpdater;

    @Autowired
    PlaceMongoRepository placeRepository;

    @AfterEach
    void tearDown() {
        placeRepository.deleteAll();
    }

    @DisplayName("Place 정보가 업데이트 된다.")
    @Test
    void update() {
        // given
        Place init = PlaceFixture.get();
        Place place = placeRepository.save(PlaceEntity.from(init)).toPlace();
        Menu menu = new Menu("testMenu", 90000, "testMenu");

        place.addMenu("testGroup", menu);

        // when
        Place update = placeUpdater.update(place);

        // then
        assertThat(update.getMenuGroups().contains(new MenuGroup("testGroup", Set.of(new Menu("testMenu", 90000, "testMenu")))));
    }
}