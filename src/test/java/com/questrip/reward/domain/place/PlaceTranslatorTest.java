package com.questrip.reward.domain.place;

import com.questrip.reward.fixture.PlaceFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PlaceTranslatorTest {

    @Autowired
    PlaceTranslator placeTranslator;

    @Autowired
    PlaceRepository placeRepository;

    @DisplayName("")
    @Test
    void translatePlace() {
        // given
        Place saved = placeRepository.save(PlaceFixture.get());

        // when
        placeTranslator.translateAllLanguages(saved);
        placeTranslator.addTranslateMenuAllLanguages(saved.getId(), PlaceFixture.getMenuGroups3());

        // then
    }
}