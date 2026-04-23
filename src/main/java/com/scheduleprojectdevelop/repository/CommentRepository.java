package com.scheduleprojectdevelop.repository;

import com.scheduleprojectdevelop.entity.Comment;
import com.scheduleprojectdevelop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findBySchedule_ScheduleIdAndCommentId(Long scheduleId, Long CommentId);

    List<Comment> findAllBySchedule_ScheduleId(Long scheduleId);
    void deleteBySchedule(Schedule schedule);
}
