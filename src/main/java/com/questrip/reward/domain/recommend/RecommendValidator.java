package com.questrip.reward.domain.recommend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecommendValidator {

    private final RecommendRepository recommendRepository;

    public void validateProgressRecommend(Long userId) {
        recommendRepository.checkForExistingInProgressRecommendation(userId);
    }
}
