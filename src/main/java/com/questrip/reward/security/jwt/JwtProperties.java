package com.questrip.reward.security.jwt;

public interface JwtProperties {
    long ACCESS_TOKEN_EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000L; // 7일
    long REFRESH_TOKEN_EXPIRE_TIME = 365 * 24 * 60 * 60 * 1000L; // 365일
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}