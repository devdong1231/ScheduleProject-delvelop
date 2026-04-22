package com.scheduleprojectdevelop.service;


import com.scheduleprojectdevelop.Validator;
import com.scheduleprojectdevelop.dto.commentDto.*;
import com.scheduleprojectdevelop.entity.*;
import com.scheduleprojectdevelop.exception.*;
import com.scheduleprojectdevelop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final Validator validator;

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
        Comment comment = findComment(scheduleId, commentId);
        return new GetOneCommentResponse(comment.getComments(),
                comment.getCommentId(),
                comment.getUser().getUserId(),
                comment.getCreatedAt(),
                comment.getUpdatedAt());
    }

    @Transactional(readOnly = true)
    public List<GetOneCommentResponse> getAllComment(Long scheduleId) {
        scheduleRepository.findById(scheduleId).orElseThrow(ScheduleNotFoundException::new);

        return commentRepository.findAllBySchedule_ScheduleId(scheduleId).stream()
                .map(comment -> new GetOneCommentResponse(
                        comment.getComments(),
                        comment.getCommentId(),
                        comment.getUser().getUserId(),
                        comment.getCreatedAt(),
                        comment.getUpdatedAt()
                )).toList();
    }

    @Transactional
    public UpdateCommentResponse updateComment(Long scheduleId, Long commentId, Long userId, UpdateCommentRequest request) {
        Comment comment = findComment(scheduleId, commentId);
        validator.validateAuthor(comment.getUser().getUserId(), userId);

        comment.update(request.getComments());
        return new UpdateCommentResponse(comment.getComments(),
                comment.getCommentId(),
                comment.getUser().getUserId(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

    @Transactional
    public void deleteComment(Long scheduleId, Long commentId, Long userId) {
        Comment comment = findComment(scheduleId, commentId);
        validator.validateAuthor(comment.getUser().getUserId(), userId);

        commentRepository.delete(comment);
    }

    private Comment findComment(Long scheduleId, Long commentId) {
        return commentRepository.findBySchedule_ScheduleIdAndCommentId(scheduleId, commentId).orElseThrow(
                CommentNotFoundException::new
        );
    }

}
