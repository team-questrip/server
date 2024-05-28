package com.questrip.reward.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("localhost:5173", "https://questrips.com")
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
