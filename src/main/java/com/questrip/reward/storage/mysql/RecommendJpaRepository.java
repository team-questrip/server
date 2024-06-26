package com.questrip.reward.storage.mysql;

import com.questrip.reward.domain.recommend.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendJpaRepository extends JpaRepository<RecommendEntity, Long> {
    Optional<RecommendEntity> findByUserIdAndStatus(Long userId, Recommend.Status status);
}
