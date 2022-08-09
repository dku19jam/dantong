package com.jaeun.dantong.domain.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id @GeneratedValue
    private Long id;
    private String email;
    private String accessToken;
    private String refreshToken;
    private String refreshTokenExpirationAt;

    public RefreshToken(String email, String accessToken, String refreshToken, String refreshTokenExpirationAt) {
        this.email = email;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.refreshTokenExpirationAt = refreshTokenExpirationAt;
    }

//    public RefreshToken(String status, String email, String accessToken, String refreshToken, String refreshTokenExpirationAt) {
//        super(status);
//        this.email = email;
//        this.accessToken = accessToken;
//        this.refreshToken = refreshToken;
//        this.refreshTokenExpirationAt = refreshTokenExpirationAt;
//    }

}

