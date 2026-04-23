package com.scheduleprojectdevelop.exception;

import org.springframework.http.HttpStatus;

public class ArgumentMismatchException extends ServiceException {
    public ArgumentMismatchException() {
        super(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다.");
    }
}
