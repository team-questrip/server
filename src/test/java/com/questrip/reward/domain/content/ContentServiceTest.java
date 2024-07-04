package com.questrip.reward.domain.content;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContentServiceTest {

    @Autowired
    ContentService contentService;

    @DisplayName("")
    @Test
    void getDefaultBlock() {
        // given

        // when
        contentService.saveDefaultBlock("8860f178-e89f-488c-b68b-a0ac414e25de");

        // then
    }
}