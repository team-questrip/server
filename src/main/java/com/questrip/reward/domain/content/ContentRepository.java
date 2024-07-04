package com.questrip.reward.domain.content;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository {

    Content findByPageId(String pageId);

    void update(Content content);

    List<Content> findAll();

    ContentBlock saveBlock(ContentBlock contentBlock);
}
