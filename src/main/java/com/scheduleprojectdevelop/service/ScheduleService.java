package com.scheduleprojectdevelop.service;

import com.scheduleprojectdevelop.dto.scheduleDto.*;
import com.scheduleprojectdevelop.entity.Schedule;
import com.scheduleprojectdevelop.entity.User;
import com.scheduleprojectdevelop.exception.ArgumentMismatchException;
import com.scheduleprojectdevelop.exception.BlankArgumentException;
import com.scheduleprojectdevelop.exception.ScheduleNotFoundException;
import com.scheduleprojectdevelop.repository.ScheduleRepository;
import com.scheduleprojectdevelop.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ArgumentMismatchException("어쩌고~")
        );
        Schedule schedule = new Schedule(request.getTitle(), request.getContents(), user);
        scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser(),
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
                schedule.getUser(),
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
                    schedule.getUser(),
                    schedule.getCreatedAt(),
                    schedule.getUpdatedAt()
            ));
        }
        return results;
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request, Long userId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );

        schedule.update(request.getTitle(), request.getContents());

        return new UpdateScheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser(),
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
