package com.scheduleprojectdevelop.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ServiceException {
    public UserAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다.");
    }
}
