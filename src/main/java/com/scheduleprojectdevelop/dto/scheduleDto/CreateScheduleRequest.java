package com.scheduleprojectdevelop.dto.scheduleDto;

import com.scheduleprojectdevelop.entity.User;
import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    private String title;
    private String contents;
    private Long userId;
}
