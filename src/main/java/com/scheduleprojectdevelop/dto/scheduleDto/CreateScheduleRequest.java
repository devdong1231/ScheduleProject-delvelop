package com.scheduleprojectdevelop.dto.scheduleDto;

import com.scheduleprojectdevelop.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateScheduleRequest {

    @NotBlank(message = "제목은 필수 입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입니다.")
    private String contents;
}
