package com.example.week9_schedule.controller;

import com.example.week9_schedule.dto.ScheduleRequestDto;
import com.example.week9_schedule.dto.ScheduleResponseDto;
import com.example.week9_schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@ResponseBody ScheduleRequestDto dto){
        return ResponseEntity.ok(scheduleService.saveSchedule(dto));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> findSchedules(){
        return ResponseEntity.ok(scheduleService.findSchedules());
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleOne(@PathVariable Long id){
        return ResponseEntity.ok(scheduleService.findScheduleOne(id));
    }

    @PutMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id, @RequestBody ScheduleRequestDto dto){
        return ResponseEntity.ok(id, dto);
    }

    @DeleteMapping("/schedules/{id}")
    public void delete(@PathVariable Long id){
        scheduleService.delete(id);
    }
}
