package com.scheduleprojectdevelop.validator;

import com.scheduleprojectdevelop.exception.UserMismatchException;
import org.springframework.stereotype.Component;

@Component
public class AuthValidator {
    public void validateAuthor(Long authorId, Long currentUserId){
        if (!authorId.equals(currentUserId)) {
            throw new UserMismatchException();
        }
    }
}
