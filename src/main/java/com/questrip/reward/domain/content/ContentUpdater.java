package com.questrip.reward.domain.content;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContentUpdater {

    private final ContentRepository contentRepository;

    public void update(Content content) {
        contentRepository.update(content);
    }
}
