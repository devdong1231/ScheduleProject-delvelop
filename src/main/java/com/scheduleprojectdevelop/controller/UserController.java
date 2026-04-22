package com.scheduleprojectdevelop.controller;

import com.scheduleprojectdevelop.dto.AuthDto.SessionUser;
import com.scheduleprojectdevelop.dto.userDto.*;
import com.scheduleprojectdevelop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<GetOneUserResponse> getOneUser(@PathVariable Long userId){
        GetOneUserResponse result = userService.getOneUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping
    public ResponseEntity<List<GetOneUserResponse>> getAllUser(){
        List<GetOneUserResponse> results = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable Long userId, UpdateUserRequest request,
                                                         @SessionAttribute(name="loginUser")SessionUser sessionUser){
        UpdateUserResponse result = userService.updateUser(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
