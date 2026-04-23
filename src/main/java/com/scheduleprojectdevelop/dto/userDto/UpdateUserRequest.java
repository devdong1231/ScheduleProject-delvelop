package com.scheduleprojectdevelop.dto.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UpdateUserRequest {

    @NotBlank
    @Pattern(regexp = "^[가-힣]{1,5}$", message = "이름은 한글 1~5자여야 합니다.")
    private String userName;

    @NotBlank
    @Email(message = "이메일은 필수입니다.")
    private String email;
}
