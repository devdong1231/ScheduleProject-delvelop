package com.scheduleprojectdevelop.dto.scheduleDto;

import com.scheduleprojectdevelop.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetSchedulePageResponse {
    private final Long scheduleId;
    private final String title;
    private final String contents;
    private final Long commentCount;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public GetSchedulePageResponse(Long scheduleId, String title, String contents, Long commentCount, String userName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.contents = contents;
        this.commentCount = commentCount;
        this.userName = userName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
