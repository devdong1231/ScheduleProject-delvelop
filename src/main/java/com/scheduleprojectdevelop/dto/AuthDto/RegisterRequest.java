package com.scheduleprojectdevelop.dto.AuthDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterRequest {

    @NotBlank(message = "이름은 한글 1~5자여야 합니다.")
    @Pattern(regexp = "^[가-힣]{1,5}$")
    private String userName;

    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Email
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Size(min = 8)
    private String password;
}
