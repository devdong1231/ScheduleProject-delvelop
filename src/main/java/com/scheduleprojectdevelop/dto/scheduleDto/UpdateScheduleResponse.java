package com.scheduleprojectdevelop.dto.scheduleDto;

import com.scheduleprojectdevelop.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateScheduleResponse {
    private final Long scheduleId;
    private final String title;
    private final String contents;
    private final User user;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public UpdateScheduleResponse(Long scheduleId, String title, String contents, User user, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.contents = contents;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
