package com.scheduleprojectdevelop.dto.userDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetOneUserResponse {
    private final Long userId;
    private final String userName;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public GetOneUserResponse(Long userId, String userName, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
