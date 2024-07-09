package com.questrip.reward.storage.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPreferenceJpaRepository extends JpaRepository<UserPreferenceEntity, Long> {
    Optional<UserPreferenceEntity> findByUserId(Long userId);

    Optional<UserPreferenceEntity> findTopByUserIdOrderByIdDesc(Long userId);
}
