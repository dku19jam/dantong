package com.jaeun.dantong.domain.dto.response;

import lombok.Data;

@Data
public class TokenResponse {

    private final String accessToken;
    private final Long refreshId;
}
