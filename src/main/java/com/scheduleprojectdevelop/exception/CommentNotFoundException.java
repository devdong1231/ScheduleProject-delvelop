package com.scheduleprojectdevelop.exception;

import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends ServiceException {
    public CommentNotFoundException() {
        super(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다.");
    }
}
