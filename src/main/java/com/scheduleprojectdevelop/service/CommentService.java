package com.scheduleprojectdevelop.service;


import com.scheduleprojectdevelop.AuthValidator;
import com.scheduleprojectdevelop.dto.commentDto.*;
import com.scheduleprojectdevelop.entity.Comment;
import com.scheduleprojectdevelop.entity.Schedule;
import com.scheduleprojectdevelop.entity.User;
import com.scheduleprojectdevelop.exception.*;
import com.scheduleprojectdevelop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final AuthValidator authValidator;

    @Transactional
    public CreateCommentResponse create(Long scheduleId, Long userId, CreateCommentRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new
        );
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ScheduleNotFoundException::new
        );

        Comment comment = new Comment(user, schedule, request.getComments());
        commentRepository.save(comment);
        return new CreateCommentResponse(comment.getComments());
    }

    @Transactional(readOnly = true)
    public GetOneCommentResponse getOneComment(Long scheduleId, Long commentId) {
        Comment comment = getComment(scheduleId, commentId);
        return new GetOneCommentResponse(comment.getComments(),
                comment.getCommentId(),
                comment.getUser().getUserName(),
                comment.getCreatedAt(),
                comment.getUpdatedAt());
    }

    @Transactional(readOnly = true)
    public List<GetOneCommentResponse> getAllComment(Long scheduleId) {
        scheduleRepository.findById(scheduleId).orElseThrow(ScheduleNotFoundException::new);
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
    public UpdateCommentResponse updateComment(Long scheduleId, Long commentId, Long userId, UpdateCommentRequest request) {
        Comment comment = getComment(scheduleId, commentId);
        authValidator.validateAuthor(comment.getUser().getUserId(), userId);

        comment.update(request.getComments());
        return new UpdateCommentResponse(comment.getComments(), comment.getCommentId(), comment.getUser().getUserName(), comment.getCreatedAt(), comment.getUpdatedAt());
    }

    @Transactional
    public void deleteComment(Long scheduleId, Long commentId, Long userId) {
        Comment comment = getComment(scheduleId, commentId);
        authValidator.validateAuthor(comment.getUser().getUserId(), userId);

        commentRepository.delete(comment);
    }

    private Comment getComment(Long scheduleId, Long commentId) {
        return commentRepository.findBySchedule_ScheduleIdAndCommentId(scheduleId, commentId).orElseThrow(
                CommentNotFoundException::new
        );
    }

}
