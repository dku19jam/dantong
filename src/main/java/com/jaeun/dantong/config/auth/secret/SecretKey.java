package com.jaeun.dantong.config.auth.secret;

import lombok.Getter;

@Getter
public class SecretKey {

    private final String jwtSecretKey;
    private final Long jwtValidityTime;
    private final Long refreshValidityTime;

    public SecretKey(String jwtSecretKey, Long jwtValidityTime, Long refreshValidityTime) {
        this.jwtSecretKey = jwtSecretKey;
        this.jwtValidityTime = jwtValidityTime;
        this.refreshValidityTime = refreshValidityTime;
    }
}
