package com.questrip.reward.domain.content;

import com.questrip.reward.api.v1.response.BlockResponse;
import com.questrip.reward.api.v1.response.PageResponse;
import com.questrip.reward.client.NotionClient;
import com.questrip.reward.client.response.NotionPageResult;
import com.questrip.reward.support.response.SliceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final NotionClient notionClient;

    public List<Page> getPages() {
        return notionClient.getPageList("183385c0793a49859c61806b09d08cbc")
                .getResults();
    }

    public List<Block> getBlocks(String pageId) {
        return notionClient.getBlocks(pageId)
                .results();
    }
}
