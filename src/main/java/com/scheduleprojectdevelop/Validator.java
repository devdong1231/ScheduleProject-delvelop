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

    public void validateAuthor(Long authorId, Long currentUserId){
        if (!authorId.equals(currentUserId)) {
            throw new UserMismatchException();
        }
    }

    public void validateByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException();
        }
    }
}
