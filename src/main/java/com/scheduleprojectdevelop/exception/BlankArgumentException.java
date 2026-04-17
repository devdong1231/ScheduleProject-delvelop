package com.scheduleprojectdevelop.exception;

import org.springframework.http.HttpStatus;

public class BlankArgumentException extends ServiceException {

    public BlankArgumentException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
