package com.example.week9_schedule.service;

import com.example.week9_schedule.dto.ScheduleRequestDto;
import com.example.week9_schedule.dto.ScheduleResponseDto;
import com.example.week9_schedule.entity.Schedule;
import com.example.week9_schedule.repository.ScheduleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto save(ScheduleRequestDto dto){
        Schedule schedule = scheduleRepository.save(new Schedule(dto.getName(), dto.getTodoTitle(), dto.getTodo(), LocalDateTime.now(), LocalDateTime.now()));

        return new ScheduleResponseDto(schedule.getId(), schedule.getName(), schedule.getTodoTitle(), schedule.getTodo(), schedule.getCreateTime(), schedule.getUpdateTime());
    }

    public List<ScheduleResponseDto> findAll(){
        List<Schedule> schedules = scheduleRepository.findAll();

        List<ScheduleResponseDto> scheduleResponseDtos = new ArrayList<>();
        for(Schedule schedule:schedules){
            scheduleResponseDtos.add(new ScheduleResponseDto(schedule.getId(), schedule.getName(), schedule.getTodoTitle(), schedule.getTodo(), schedule.getCreateTime(), schedule.getUpdateTime()));
        }

        return scheduleResponseDtos;
    }

    public ScheduleResponseDto findOne(Long id){
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 스케줄이 없습니다.")
        );
        return new ScheduleResponseDto(schedule.getId(), schedule.getName(), schedule.getTodoTitle(), schedule.getTodo(), schedule.getCreateTime(), schedule.getUpdateTime());
    }

    @Transactional
    public ScheduleResponseDto update(Long id, ScheduleRequestDto dto){
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("헤당 id에 맞는 스케줄이 없습니다.")
        );
        schedule.update(dto.getName(), dto.getTodoTitle(), dto.getTodo(), LocalDateTime.now());
        return new ScheduleResponseDto(schedule.getId(), schedule.getName(), schedule.getTodoTitle(), schedule.getTodo(), schedule.getCreateTime(), schedule.getUpdateTime());
    }

    @Transactional
    public void delete(Long id){
        if(!scheduleRepository.existsById(id)){
            throw new IllegalArgumentException("없는 id입니다.");
        }
        scheduleRepository.deleteById(id);
    }
}
