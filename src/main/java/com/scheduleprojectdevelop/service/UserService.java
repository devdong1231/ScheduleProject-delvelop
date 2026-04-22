package com.scheduleprojectdevelop.service;

import com.scheduleprojectdevelop.dto.userDto.*;
import com.scheduleprojectdevelop.entity.User;
import com.scheduleprojectdevelop.exception.UserAlreadyExistsException;
import com.scheduleprojectdevelop.exception.UserNotFoundException;
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

    @Transactional(readOnly = true)
    public GetOneUserResponse getOneUser(Long userId) {
        User user = getUser(userId);

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
        User user = getUser(userId);
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        user.update(request.getUserName(), request.getEmail());

        return new UpdateUserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new
        );
        userRepository.delete(user);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new
        );
    }

}
