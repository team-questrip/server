package com.questrip.reward.storage;

import com.questrip.reward.domain.recommend.Recommend;
import com.questrip.reward.domain.recommend.RecommendRepository;
import com.questrip.reward.storage.mysql.RecommendEntity;
import com.questrip.reward.storage.mysql.RecommendJpaRepository;
import com.questrip.reward.storage.mysql.RecommendQueryRepository;
import com.questrip.reward.support.error.ErrorCode;
import com.questrip.reward.support.error.GlobalException;
import com.questrip.reward.support.response.SliceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
    public SliceResult<Recommend> findAllRecommendsWithStatus(Long userId, List<Recommend.Status> status, int page, int size) {
        Slice<Recommend> recommends = recommendQueryRepository.findAllRecommendStatus(userId, PageRequest.of(page, size), status)
                .map(RecommendEntity::toRecommend);

        return new SliceResult<>(recommends);
    }

    @Override
    public Recommend findProgressRecommend(Long userId) {
        return recommendJpaRepository.findByUserIdAndStatus(userId, Recommend.Status.ACCEPTED)
                .orElseThrow(() -> new GlobalException(ErrorCode.PROGRESS_RECOMMEND_NOT_FOUND))
                .toRecommend();
    }

    @Override
    @Transactional
    public Recommend update(Recommend recommend) {
        RecommendEntity recommendEntity = recommendJpaRepository.findById(recommend.getId()).orElseThrow();
        recommendEntity.updateStatus(recommend.getStatus());
        return recommendEntity.toRecommend();
    }

    @Override
    public void checkForExistingInProgressRecommendation(Long userId) {
        recommendJpaRepository.findByUserIdAndStatus(userId, Recommend.Status.ACCEPTED)
                .ifPresent(recommend -> {
                        throw new GlobalException(ErrorCode.ALREADY_EXIST_PROGRESS_RECOMMEND);
                });
    }
}
