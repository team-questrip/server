package com.questrip.reward.storage;

import com.questrip.reward.domain.recommend.Recommend;
import com.questrip.reward.domain.recommend.RecommendRepository;
import com.questrip.reward.storage.mysql.RecommendEntity;
import com.questrip.reward.storage.mysql.RecommendJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecommendCoreRepository implements RecommendRepository {

    private final RecommendJpaRepository recommendJpaRepository;

    @Override
    public Recommend save(Recommend recommend) {
        return recommendJpaRepository.save(RecommendEntity.from(recommend)).toRecommend();
    }
}
