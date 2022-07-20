package com.jaeun.dantong.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String email;
    private String password;
    private String auth;
    private String major;
    private int studentId;
}
