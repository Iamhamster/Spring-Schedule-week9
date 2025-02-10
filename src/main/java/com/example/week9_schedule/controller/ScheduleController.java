package com.example.week9_schedule.controller;

import com.example.week9_schedule.dto.ScheduleRequestDto;
import com.example.week9_schedule.dto.ScheduleResponseDto;
import com.example.week9_schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto dto){
        return ResponseEntity.ok(scheduleService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(){
        return ResponseEntity.ok(scheduleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findOne(@PathVariable Long id){
        return ResponseEntity.ok(scheduleService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id, @RequestBody ScheduleRequestDto dto){
        return ResponseEntity.ok(scheduleService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        scheduleService.delete(id);
    }
}
