package com.jaeun.dantong.config.auth;

public interface JwtProperties {
    String SECRET = "최재민";
    int EXPIRATION_TIME = 86400000;
    String TOKEN_PREFIX = "Bearer";
    String HEADER_STRING = "Authorization";
}

