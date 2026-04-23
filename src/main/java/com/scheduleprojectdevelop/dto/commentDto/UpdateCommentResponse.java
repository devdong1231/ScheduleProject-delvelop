package com.scheduleprojectdevelop.dto.commentDto;

import java.time.LocalDateTime;

public class UpdateCommentResponse {

    private final String comments;
    private final Long commentId;
    private final Long userId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public UpdateCommentResponse(String comments, Long commentId, Long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.comments = comments;
        this.commentId = commentId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
