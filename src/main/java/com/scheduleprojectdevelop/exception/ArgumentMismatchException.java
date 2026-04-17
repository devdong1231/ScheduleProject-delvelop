package com.scheduleprojectdevelop.exception;

import org.springframework.http.HttpStatus;

public class ArgumentMismatchException extends ServiceException {
    public ArgumentMismatchException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
