package com.scheduleprojectdevelop.controller;

import com.scheduleprojectdevelop.dto.AuthDto.SessionUser;
import com.scheduleprojectdevelop.dto.scheduleDto.*;
import com.scheduleprojectdevelop.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<CreateScheduleResponse> createSchedule(@Valid @RequestBody CreateScheduleRequest request,
                                                                 @SessionAttribute(name = "loginUser") SessionUser sessionUser) {
        CreateScheduleResponse result = scheduleService.createSchedule(request, sessionUser.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<GetOneScheduleResponse> getOneSchedule(@PathVariable Long scheduleId) {
        GetOneScheduleResponse result = scheduleService.getOneSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping
    public ResponseEntity<Page<GetSchedulePageResponse>> getAllSchedule(@RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "10") int size) {
        Page<GetSchedulePageResponse> results = scheduleService.getAllSchedule(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(@PathVariable Long scheduleId,
                                                                 @Valid @RequestBody UpdateScheduleRequest request,
                                                                 @SessionAttribute(name = "loginUser") SessionUser sessionUser) {
        UpdateScheduleResponse result = scheduleService.updateSchedule(scheduleId, request, sessionUser.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId,
                                               @SessionAttribute(name = "loginUser") SessionUser sessionuser) {
        scheduleService.deleteSchedule(scheduleId, sessionuser.getUserId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
