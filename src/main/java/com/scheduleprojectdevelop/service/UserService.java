package com.scheduleprojectdevelop.service;

import com.scheduleprojectdevelop.dto.userDto.CreateUserRequest;
import com.scheduleprojectdevelop.dto.userDto.CreateUserResponse;
import com.scheduleprojectdevelop.entity.User;
import com.scheduleprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request){
        //todo - 400
        User user = new User(request.getUserName(), request.getEmail());
        userRepository.save(user);
        return new CreateUserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

}
