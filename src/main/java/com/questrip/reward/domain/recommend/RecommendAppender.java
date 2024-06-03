package com.questrip.reward.domain.recommend;

import com.questrip.reward.domain.place.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecommendAppender {

    private final RecommendRepository recommendRepository;
    private final RecommendFactory recommendFactory;

    public Recommend append(Place place, Recommend recommend) {
        Recommend saved = recommendRepository.save(recommend);
        return recommendFactory.of(saved, place);
    }
}
