package com.questrip.reward.domain.recommend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecommendUpdater {

    private final RecommendRepository recommendRepository;

    public Recommend update(Recommend recommend) {
        return recommendRepository.update(recommend);
    }
}
