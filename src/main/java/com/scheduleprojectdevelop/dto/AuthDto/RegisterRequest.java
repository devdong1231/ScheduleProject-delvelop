package com.scheduleprojectdevelop.dto.AuthDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterRequest {

    @NotBlank
    @Pattern(regexp = "^[가-힣]{1,5}$", message = "이름은 한글 1~5자여야 합니다.")
    private String userName;

    @NotBlank
    @Email(message = "이메일은 필수 입력입니다.")
    private String email;

    @NotBlank
    @Size(min = 8, message = "비밀번호는 필수 입력입니다.")
    private String password;
}
