package com.questrip.reward.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class NotionAuthInterceptor implements RequestInterceptor {

    @Value("${notion.api.key}")
    private String key;

    @Override
    public void apply(RequestTemplate template) {
        template.header("Notion-Version", "2022-06-28");
        template.header("Authorization", key);
    }
}
