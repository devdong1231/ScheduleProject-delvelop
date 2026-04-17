package com.scheduleprojectdevelop.dto.scheduleDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetOneScheduleResponse {
    private final Long scheduleId;
    private final String title;
    private final String contents;
    private final Long userId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public GetOneScheduleResponse(Long scheduleId, String title, String contents, Long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
