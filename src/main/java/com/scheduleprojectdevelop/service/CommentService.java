package com.scheduleprojectdevelop.service;


import com.scheduleprojectdevelop.dto.commentDto.*;
import com.scheduleprojectdevelop.entity.Comment;
import com.scheduleprojectdevelop.entity.Schedule;
import com.scheduleprojectdevelop.entity.User;
import com.scheduleprojectdevelop.repository.CommentRepository;
import com.scheduleprojectdevelop.repository.ScheduleRepository;
import com.scheduleprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private ScheduleRepository scheduleRepository;

    @Transactional
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

    @Transactional(readOnly = true)
    public GetOneCommentResponse getOneComment(Long scheduleId, Long commentId) {
        Comment comment = commentRepository.findBySchedule_ScheduleIdAndCommentId(scheduleId, commentId);
        return new GetOneCommentResponse(comment.getComments(), comment.getCommentId(), comment.getUser().getUserName(), comment.getCreatedAt(), comment.getUpdatedAt());
    }

    @Transactional(readOnly = true)
    public List<GetOneCommentResponse> getAllComment(Long scheduleId) {
        List<Comment> comments = commentRepository.findAllBySchedule_ScheduleId(scheduleId);
        List<GetOneCommentResponse> results = new ArrayList<>();
        for (Comment comment : comments) {
            results.add(new GetOneCommentResponse(
                    comment.getComments(),
                    comment.getCommentId(),
                    comment.getUser().getUserName(),
                    comment.getCreatedAt(),
                    comment.getUpdatedAt()
            ));
        }

        return results;
    }

    @Transactional
    public UpdateCommentResponse updateComment(Long scheduleId, Long commentId, UpdateCommentRequest request) {
        Comment comment = commentRepository.findBySchedule_ScheduleIdAndCommentId(scheduleId, commentId);
        comment.update(request.getComments());
        return new UpdateCommentResponse(comment.getComments(), comment.getCommentId(), comment.getUser().getUserName(), comment.getCreatedAt(), comment.getUpdatedAt());
    }
}
