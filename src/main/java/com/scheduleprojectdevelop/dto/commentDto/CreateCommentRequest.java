package com.scheduleprojectdevelop.dto.commentDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequest {
    @Size(max = 50, message = "50자 이내로 입력해주세요.")
    @NotBlank(message = "내용은 필수 입니다.")
    private String comments;
}
