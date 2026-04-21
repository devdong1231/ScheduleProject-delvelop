package com.scheduleprojectdevelop.repository;

import com.scheduleprojectdevelop.dto.scheduleDto.GetSchedulePageResponse;
import com.scheduleprojectdevelop.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(
            value = """
                    select new com.scheduleprojectdevelop.dto.scheduleDto.GetSchedulePageResponse(
                        s.scheduleId,
                        s.title,
                        s.content,
                        count(c),
                        u.userName,
                        s.createdAt,
                        s.updatedAt
                    )
                    from schedules s
                    join s.user u
                    left join comments c on c.schedule = s
                    group by s.scheduleId, s.title, s.content, s.createdAt, s.updatedAt, u.userName
                    """,
            countQuery = """
                    select count(s)
                    from schedules s
                    """
    )
    Page<GetSchedulePageResponse> findAllWithPage(Pageable pageable);
}
