package com.questrip.reward.domain.content;

import com.questrip.reward.support.response.SliceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentAppender contentAppender;
    private final ContentReader contentReader;

    @Transactional
    public void publish(Content content) {
        contentAppender.append(content);
    }

    public Content findContent(String id) {
        return contentReader.findContentById(id);
    }

    public SliceResult<Content> findContentsBy(int page, int size) {
        return contentReader.findAllBy(page, size);
    }
}
