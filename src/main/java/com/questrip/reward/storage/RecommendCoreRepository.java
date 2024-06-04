package com.questrip.reward.storage;

import com.questrip.reward.domain.recommend.Recommend;
import com.questrip.reward.domain.recommend.RecommendRepository;
import com.questrip.reward.storage.mysql.RecommendEntity;
import com.questrip.reward.storage.mysql.RecommendJpaRepository;
import com.questrip.reward.storage.mysql.RecommendQueryRepository;
import com.questrip.reward.support.response.SliceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RecommendCoreRepository implements RecommendRepository {

    private final RecommendJpaRepository recommendJpaRepository;
    private final RecommendQueryRepository recommendQueryRepository;

    @Override
    public Recommend save(Recommend recommend) {
        return recommendJpaRepository.save(RecommendEntity.from(recommend)).toRecommend();
    }

    @Override
    public List<String> getExcludePlaceIds(Long userId, LocalDateTime start, LocalDateTime end) {
        return recommendQueryRepository.getExcludePlaceId(userId, start, end);
    }

    @Override
    public SliceResult<Recommend> findAllKeptRecommend(Long userId, int page, int size) {
        Slice<Recommend> recommends = recommendQueryRepository.findAllKeptRecommend(userId, PageRequest.of(page, size))
                .map(RecommendEntity::toRecommend);

        return new SliceResult<>(recommends);
    }
}
