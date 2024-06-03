package com.questrip.reward.domain.recommend;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RecommendRepository {
    Recommend save(Recommend recommend);

    List<String> getExcludePlaceIds(Long userId, LocalDateTime start, LocalDateTime end);
}
