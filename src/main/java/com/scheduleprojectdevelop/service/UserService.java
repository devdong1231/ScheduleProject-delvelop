package com.scheduleprojectdevelop.service;

import com.scheduleprojectdevelop.dto.scheduleDto.UpdateScheduleResponse;
import com.scheduleprojectdevelop.dto.userDto.*;
import com.scheduleprojectdevelop.entity.User;
import com.scheduleprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) {
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

    @Transactional(readOnly = true)
    public GetOneUserResponse getOneUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("adsf") // todo - 400
        );

        return new GetOneUserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetOneUserResponse> getAllUser() {
        List<User> users = userRepository.findAll();
        List<GetOneUserResponse> results = new ArrayList<>();

        for (User user : users) {
            results.add(new GetOneUserResponse(
                    user.getUserId(),
                    user.getUserName(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getUpdatedAt()
            ));
        }

        return results;
    }

    @Transactional
    public UpdateUserResponse updateUser(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("adfs") // todo - 404
        );

        user.update(request.getUserName(), request.getEmail());

        return new UpdateUserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

}
