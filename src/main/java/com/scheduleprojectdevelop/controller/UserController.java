package com.scheduleprojectdevelop.controller;

import com.scheduleprojectdevelop.dto.userDto.CreateUserRequest;
import com.scheduleprojectdevelop.dto.userDto.CreateUserResponse;
import com.scheduleprojectdevelop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest request){
        CreateUserResponse result = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
