package com.scheduleprojectdevelop.dto.scheduleDto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    private String title;
    private String contents;
    private String author;
}
