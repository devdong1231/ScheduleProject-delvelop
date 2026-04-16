package com.scheduleprojectdevelop.controller;

import com.scheduleprojectdevelop.dto.userDto.CreateUserRequest;
import com.scheduleprojectdevelop.dto.userDto.CreateUserResponse;
import com.scheduleprojectdevelop.dto.userDto.GetOneUserResponse;
import com.scheduleprojectdevelop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest request){
        CreateUserResponse result = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<GetOneUserResponse> getOneUser(@PathVariable Long userId){
        GetOneUserResponse result = userService.getOneUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users")
    public ResponseEntity<List<GetOneUserResponse>> getAllUser(){
        List<GetOneUserResponse> results = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }
}
