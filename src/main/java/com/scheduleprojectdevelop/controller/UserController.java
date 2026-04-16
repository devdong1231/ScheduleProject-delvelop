package com.scheduleprojectdevelop.controller;

import com.scheduleprojectdevelop.dto.scheduleDto.UpdateScheduleResponse;
import com.scheduleprojectdevelop.dto.userDto.*;
import com.scheduleprojectdevelop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/users/{userId}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable Long userId, UpdateUserRequest request){
        UpdateUserResponse result = userService.updateUser(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
