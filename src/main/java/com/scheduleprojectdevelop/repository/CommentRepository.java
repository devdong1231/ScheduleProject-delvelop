package com.scheduleprojectdevelop.repository;

import com.scheduleprojectdevelop.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findBySchedule_ScheduleIdAndCommentId(Long scheduleId, Long CommentId);

    List<Comment> findAllBySchedule_ScheduleId(Long scheduleId);
}
