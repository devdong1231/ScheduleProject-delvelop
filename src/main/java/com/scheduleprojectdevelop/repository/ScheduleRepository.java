package com.scheduleprojectdevelop.repository;

import com.scheduleprojectdevelop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
