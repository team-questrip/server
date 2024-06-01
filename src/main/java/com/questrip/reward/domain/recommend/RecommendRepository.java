package com.questrip.reward.domain.recommend;

import org.springframework.stereotype.Repository;

@Repository
public interface RecommendRepository {
    Recommend save(Recommend recommend);
}
