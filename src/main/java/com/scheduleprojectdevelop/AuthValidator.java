package com.scheduleprojectdevelop;

import com.scheduleprojectdevelop.exception.UserMismatchException;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthValidator {
    public void validateAuthor(Long authorId, Long currentUserId){
        if (!authorId.equals(currentUserId)) {
            throw new UserMismatchException();
        }
    }
}
