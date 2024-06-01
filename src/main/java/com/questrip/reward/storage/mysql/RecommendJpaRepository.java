package com.questrip.reward.storage.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendJpaRepository extends JpaRepository<RecommendEntity, Long> {
}
