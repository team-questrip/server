package com.questrip.reward.domain.content;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.questrip.reward.fixture.ContentFixture;
import com.questrip.reward.storage.mongo.ContentEntity;
import com.questrip.reward.storage.mongo.ContentMongoRepository;
import com.questrip.reward.support.response.SliceResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ContentReaderTest {

    @Autowired
    ContentReader contentReader;

    @Autowired
    ContentMongoRepository contentMongoRepository;

    @AfterEach
    void tearDown() {
        contentMongoRepository.deleteAll();
    }

    @DisplayName("Content Id로 Content를 찾는다.")
    @Test
    void findContentById() {
        // given
        Content initContent = ContentFixture.get();
        ContentEntity saved = contentMongoRepository.save(ContentEntity.from(initContent));

        // when
        Content content = contentReader.findContentById(saved.getId());

        // then
        assertThat(content).extracting(
                "title", "category", "tags", "images", "sections"
        ).containsExactly(
                content.getTitle(), content.getCategory(), content.getTags(), content.getImages(), content.getSections()
        );
    }

    @DisplayName("Content를 페이징하여 가져온다.")
    @Test
    void findAllBy() {
        // given
        List<ContentEntity> contents = new ArrayList<>();
        for(int i=0; i<10; i++) {
            contents.add(ContentEntity.from(ContentFixture.get(i)));
        }

        contentMongoRepository.saveAll(contents);

        // when
        SliceResult<Content> result = contentReader.findAllBy(0, 5);

        // then
        assertThat(result.getContent().size()).isEqualTo(5);
        assertThat(result.getContent()).extracting(
                "title"
        ).containsExactly(
                "test content9", "test content8", "test content7", "test content6", "test content5"
        );
        assertThat(result.isHasNext()).isTrue();
    }

    @DisplayName("Content를 페이징하여 가져온다.")
    @Test
    void findAllBy2() {
        // given
        List<ContentEntity> contents = new ArrayList<>();
        for(int i=0; i<10; i++) {
            contents.add(ContentEntity.from(ContentFixture.get(i)));
        }

        contentMongoRepository.saveAll(contents);

        // when
        SliceResult<Content> result = contentReader.findAllBy(1, 5);

        // then
        assertThat(result.getContent().size()).isEqualTo(5);
        assertThat(result.getContent()).extracting(
                "title"
        ).containsExactly(
                "test content4", "test content3", "test content2", "test content1", "test content0"
        );
        assertThat(result.isHasNext()).isFalse();
    }
}