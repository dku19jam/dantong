package com.jaeun.dantong.domain.dto.response;


import com.jaeun.dantong.domain.entity.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponse {

    private Long id;
    private String name;

    public UserResponse(User user) {
        this(user.getId(), user.getName());
    }

    public UserResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
