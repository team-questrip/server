package com.questrip.reward.client;

import com.questrip.reward.client.response.NotionBlockResult;
import com.questrip.reward.client.response.NotionPageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "notionApi", url = "https://api.notion.com/v1", configuration = FeignConfig.class)
public interface NotionClient {

    @PostMapping("/databases/{databaseId}/query")
    NotionPageResult getPageList(@PathVariable String databaseId);

    @GetMapping("/blocks/{pageId}/children")
    NotionBlockResult getBlocks(@PathVariable String pageId);
}
