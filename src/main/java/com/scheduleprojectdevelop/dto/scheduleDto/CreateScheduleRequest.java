package com.scheduleprojectdevelop.dto.scheduleDto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    private String title;
    private String contents;
    private String author;
}
