package com.scheduleprojectdevelop.service;

import com.scheduleprojectdevelop.AuthValidator;
import com.scheduleprojectdevelop.dto.scheduleDto.*;
import com.scheduleprojectdevelop.entity.Schedule;
import com.scheduleprojectdevelop.entity.User;
import com.scheduleprojectdevelop.exception.ArgumentMismatchException;
import com.scheduleprojectdevelop.exception.ScheduleNotFoundException;
import com.scheduleprojectdevelop.exception.UserNotFoundException;
import com.scheduleprojectdevelop.repository.ScheduleRepository;
import com.scheduleprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final AuthValidator authValidator;

    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new
        );
        Schedule schedule = new Schedule(request.getTitle(), request.getContents(), user);
        scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getUserId(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt());
    }

    @Transactional(readOnly = true)
    public GetOneScheduleResponse getOneSchedule(Long scheduleId) {
        Schedule schedule = getSchedule(scheduleId);

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
    public Page<GetSchedulePageResponse> getAllSchedule(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("updatedAt").descending()
        );

        return scheduleRepository.findAllWithPage(pageable);
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request, Long userId) {
        Schedule schedule = getSchedule(scheduleId);
        authValidator.validateAuthor(schedule.getUser().getUserId(), userId);

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
    public void deleteSchedule(Long scheduleId, Long userId) {
        Schedule schedule = getSchedule(scheduleId);
        authValidator.validateAuthor(schedule.getUser().getUserId(), userId);

        scheduleRepository.delete(schedule);
    }

    private Schedule getSchedule(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(
                ScheduleNotFoundException::new
        );
    }

}
