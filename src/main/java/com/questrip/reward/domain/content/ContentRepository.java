package com.questrip.reward.domain.content;

import com.questrip.reward.support.response.SliceResult;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository {
    Content append(Content content);

    Content findById(String id);

    SliceResult<Content> findAllBy(int page, int size);
}
