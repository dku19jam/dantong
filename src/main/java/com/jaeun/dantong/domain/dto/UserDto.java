package com.jaeun.dantong.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String email;
    private String name;
    private String password;
    private String major;
    private int studentId;
}
