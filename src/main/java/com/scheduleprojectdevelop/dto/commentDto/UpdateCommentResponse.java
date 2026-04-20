package com.scheduleprojectdevelop.dto.commentDto;

import java.time.LocalDateTime;

public class UpdateCommentResponse {

    private final String comments;
    private final Long commentId;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public UpdateCommentResponse(String comments, Long commentId, String userName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.comments = comments;
        this.commentId = commentId;
        this.userName = userName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
