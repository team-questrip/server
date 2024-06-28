package com.questrip.reward.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotionConfig {

    @Bean
    public NotionAuthInterceptor feignInterceptor() {
        return new NotionAuthInterceptor();
    }
}
