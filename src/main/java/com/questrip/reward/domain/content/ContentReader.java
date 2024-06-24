package com.questrip.reward.domain.content;

import com.questrip.reward.support.response.SliceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContentReader {

    private final ContentRepository contentRepository;

    public Content findContentById(String id) {
        return contentRepository.findById(id);
    }

    public SliceResult<Content> findAllBy(int page, int size) {
        return contentRepository.findAllBy(page, size);
    }
}
