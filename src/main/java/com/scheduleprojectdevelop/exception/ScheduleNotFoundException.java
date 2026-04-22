package com.scheduleprojectdevelop.exception;

import org.springframework.http.HttpStatus;

public class ScheduleNotFoundException extends ServiceException{

    public ScheduleNotFoundException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 일정입니다.");
    }
}
