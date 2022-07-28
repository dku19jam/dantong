package com.jaeun.dantong.domain.dto.request;

import lombok.Data;

@Data
public class AcceptGroupRequest {
    private Long groupId;
    private Long memberId;

}
