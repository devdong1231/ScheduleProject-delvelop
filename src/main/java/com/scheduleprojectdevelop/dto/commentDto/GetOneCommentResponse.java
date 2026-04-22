package com.scheduleprojectdevelop.dto.commentDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetOneCommentResponse {

    private final String comments;
    private final Long commentId;
    private final Long userId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public GetOneCommentResponse(String comments, Long commentId, Long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.comments = comments;
        this.commentId = commentId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
