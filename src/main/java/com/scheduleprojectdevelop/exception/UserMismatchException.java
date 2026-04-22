package com.scheduleprojectdevelop.exception;

import org.springframework.http.HttpStatus;

public class UserMismatchException extends ServiceException {
    public UserMismatchException() {
        super(HttpStatus.FORBIDDEN, "권한이 없습니다.");
    }
}
