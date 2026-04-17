package com.scheduleprojectdevelop.service;

import com.scheduleprojectdevelop.dto.scheduleDto.*;
import com.scheduleprojectdevelop.entity.Schedule;
import com.scheduleprojectdevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
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
        // todo - 400 처리
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
                () -> new IllegalStateException("asdf") // todo - 400 처리
        );
        // todo - 404 처리

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
                () -> new IllegalStateException("asdf") // todo - 400 처리
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
                () -> new IllegalStateException("asdf") // todo - 400 처리
        );

        scheduleRepository.delete(schedule);
    }

}
