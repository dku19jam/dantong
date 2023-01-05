package com.jaeun.dantong.domain.dto.request;

import com.jaeun.dantong.domain.entity.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class CreateBoardRequest {

    @NotBlank(message = "제목을 입력해 주세요.")
    @Length(min = 2, max = 50, message = "2자 이상 50자 이내의 제목을 입력해 주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해 주세요")
    private String content;
}
