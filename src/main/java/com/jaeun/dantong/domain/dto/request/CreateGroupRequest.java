package com.jaeun.dantong.domain.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class CreateGroupRequest {

    @NotBlank(message = "그룹명을 입력해주세요")
    @Length(min = 1, max = 30, message = "30자 이내로 입력해주세요")
    private String groupName;
}
