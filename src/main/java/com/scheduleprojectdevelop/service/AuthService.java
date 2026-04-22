package com.scheduleprojectdevelop.service;

import com.scheduleprojectdevelop.config.PasswordEncoder;
import com.scheduleprojectdevelop.dto.AuthDto.LoginRequest;
import com.scheduleprojectdevelop.dto.AuthDto.LoginResponse;
import com.scheduleprojectdevelop.dto.AuthDto.RegisterRequest;
import com.scheduleprojectdevelop.dto.AuthDto.RegisterResponse;
import com.scheduleprojectdevelop.entity.User;
import com.scheduleprojectdevelop.exception.*;
import com.scheduleprojectdevelop.repository.UserRepository;
import com.scheduleprojectdevelop.validator.DomainValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DomainValidator domainValidator;

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        domainValidator.validateByEmail(request.getEmail());
        String encoded = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getUserName(), request.getEmail(), encoded);

        userRepository.save(user);
        return new RegisterResponse(user.getUserId());
    }


    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new ArgumentMismatchException("이메일 또는 비밀번호가 일치하지 않습니다.")
        );
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ArgumentMismatchException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        return new LoginResponse(user.getUserId(), user.getEmail());
    }

}
