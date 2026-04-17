package com.scheduleprojectdevelop.dto.AuthDto;

import lombok.Getter;

@Getter
public class RegisterResponse {
    private final Long userId;

    public RegisterResponse(Long userId) {
        this.userId = userId;
    }
}
