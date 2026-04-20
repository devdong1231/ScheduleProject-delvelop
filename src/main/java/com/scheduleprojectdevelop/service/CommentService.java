package com.scheduleprojectdevelop.service;


import com.scheduleprojectdevelop.dto.commentDto.CreateCommentRequest;
import com.scheduleprojectdevelop.dto.commentDto.CreateCommentResponse;
import com.scheduleprojectdevelop.entity.Comment;
import com.scheduleprojectdevelop.entity.Schedule;
import com.scheduleprojectdevelop.entity.User;
import com.scheduleprojectdevelop.repository.CommentRepository;
import com.scheduleprojectdevelop.repository.ScheduleRepository;
import com.scheduleprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private ScheduleRepository scheduleRepository;

    public CreateCommentResponse create(Long scheduleId, Long userId, CreateCommentRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("adf") // todo - 예외 처리
        );
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("adf") // todo - 예외 처리
        );

        Comment comment = new Comment(user, schedule, request.getComments());
        commentRepository.save(comment);
        return new CreateCommentResponse(comment.getComments());
    }
}
