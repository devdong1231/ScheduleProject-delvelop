package com.scheduleprojectdevelop.controller;


import com.scheduleprojectdevelop.dto.AuthDto.*;
import com.scheduleprojectdevelop.entity.User;
import com.scheduleprojectdevelop.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(RegisterRequest request) {
        User user = authService.register(request);
        RegisterResponse result = new RegisterResponse(user.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        User user = authService.login(request);
        SessionUser sessionUser = new SessionUser(user.getUserId(), user.getEmail());
        session.setAttribute("loginUser", sessionUser);

        LoginResponse result = new LoginResponse(user.getUserId(), user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, HttpSession session) {
        if (sessionUser == null) {
            return ResponseEntity.badRequest().build();
        }
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
