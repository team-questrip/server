package com.questrip.reward.domain.content;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContentAppender {

    private final ContentRepository contentRepository;

    public Content append(Content content) {
        return contentRepository.append(content);
    }
}
