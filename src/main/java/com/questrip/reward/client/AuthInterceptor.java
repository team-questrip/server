package com.questrip.reward.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class AuthInterceptor implements RequestInterceptor {

    @Value("${notion.api.key}")
    private String notionKey;

    @Value("${deepl.api.key}")
    private String deeplKey;

    private final static String AUTHORIZATION = "Authorization";
    private final static String NOTION_VERSION = "Notion-Version";
    private final static String DEEPL_AUTH_KEY = "DeepL-Auth-Key";

    @Override
    public void apply(RequestTemplate template) {
        String url = template.feignTarget().url();
        if(url.contains("notion")){
            template.header(NOTION_VERSION, "2022-06-28");
            template.header(AUTHORIZATION, notionKey);
        } else if(url.contains("deepl")){
            template.header(AUTHORIZATION, DEEPL_AUTH_KEY+" "+deeplKey);
        }
    }
}
