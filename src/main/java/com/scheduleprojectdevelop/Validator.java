package com.scheduleprojectdevelop;

import com.scheduleprojectdevelop.exception.UserAlreadyExistsException;
import com.scheduleprojectdevelop.exception.UserMismatchException;
import com.scheduleprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Validator {
    private final UserRepository userRepository;

    // 작성자와 로그인 유저가 동일한지 확인
    public void validateAuthor(Long authorId, Long currentUserId){
        if (!authorId.equals(currentUserId)) {
            throw new UserMismatchException();
        }
    }

    // 이미 존재하는 이메일인지 확인(중복 회원가입 방지)
    public void validateByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException();
        }
    }
}
