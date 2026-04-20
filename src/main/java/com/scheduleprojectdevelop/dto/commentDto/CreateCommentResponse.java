package com.scheduleprojectdevelop.dto.commentDto;

import lombok.Getter;

@Getter
public class CreateCommentResponse {
    private final String comment;

    public CreateCommentResponse(String comment) {
        this.comment = comment;
    }
}
