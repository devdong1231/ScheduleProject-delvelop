package com.scheduleprojectdevelop.service;

import com.scheduleprojectdevelop.config.PasswordEncoder;
import com.scheduleprojectdevelop.dto.AuthDto.LoginRequest;
import com.scheduleprojectdevelop.dto.AuthDto.RegisterRequest;
import com.scheduleprojectdevelop.entity.User;
import com.scheduleprojectdevelop.exception.ArgumentMismatchException;
import com.scheduleprojectdevelop.exception.UserNotFoundException;
import com.scheduleprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserNotFoundException("이미 존재하는 사용자입니다.");
        }
        String encoded = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getUserName(), request.getEmail(), encoded);

        return userRepository.save(user);
    }


    @Transactional(readOnly = true)
    public User login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new ArgumentMismatchException("이메일 또는 비밀번호가 일치하지 않습니다.")
        );
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new ArgumentMismatchException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

}
