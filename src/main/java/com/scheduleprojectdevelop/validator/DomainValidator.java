package com.scheduleprojectdevelop.validator;

import com.scheduleprojectdevelop.exception.UserAlreadyExistsException;
import com.scheduleprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DomainValidator {
    private final UserRepository userRepository;

    public void validateByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException();
        }
    }
}
