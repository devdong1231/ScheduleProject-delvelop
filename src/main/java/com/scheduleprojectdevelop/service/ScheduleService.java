package com.scheduleprojectdevelop.service;

import com.scheduleprojectdevelop.dto.scheduleDto.*;
import com.scheduleprojectdevelop.entity.Schedule;
import com.scheduleprojectdevelop.exception.BlankArgumentException;
import com.scheduleprojectdevelop.exception.ScheduleNotFoundException;
import com.scheduleprojectdevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new BlankArgumentException("제목은 필수입니다.");
        }

        if (request.getContents() == null || request.getContents().isBlank()) {
            throw new BlankArgumentException("내용은 필수입니다.");
        }

        if (request.getUserId() == null) {
            throw new BlankArgumentException("작성자 ID는 필수입니다.");
        }
        Schedule schedule = new Schedule(request.getTitle(), request.getContents(), request.getUserId());
        scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUserId(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt());
    }

    @Transactional(readOnly = true)
    public GetOneScheduleResponse getOneSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );


        return new GetOneScheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUserId(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetOneScheduleResponse> getAllSchedule() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<GetOneScheduleResponse> results = new ArrayList<>();
        for (Schedule schedule : schedules) {
            results.add(new GetOneScheduleResponse(
                    schedule.getScheduleId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getUserId(),
                    schedule.getCreatedAt(),
                    schedule.getUpdatedAt()
            ));
        }
        return results;
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );

        schedule.update(request.getTitle(), request.getContents(), request.getUserId());

        return new UpdateScheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUserId(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );

        scheduleRepository.delete(schedule);
    }

}
