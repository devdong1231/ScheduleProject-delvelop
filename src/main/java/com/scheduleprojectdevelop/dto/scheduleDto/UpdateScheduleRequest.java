package com.scheduleprojectdevelop.dto.scheduleDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateScheduleRequest {

    @Size(max = 20, message = "20자 이내로 입력해주세요.")
    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @Size(max = 50, message = "50자 이내로 입력해주세요.")
    @NotBlank(message = "내용은 필수입니다.")
    private String contents;
}
