package com.example.week9_schedule.service;

import com.example.week9_schedule.dto.ScheduleRequestDto;
import com.example.week9_schedule.dto.ScheduleResponseDto;
import com.example.week9_schedule.entity.Schedule;
import com.example.week9_schedule.exception.CustomException;
import com.example.week9_schedule.exception.ExceptionStatus;
import com.example.week9_schedule.repository.ScheduleRepository;
import com.example.week9_schedule.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Getter
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto save(ScheduleRequestDto dto){
        Schedule schedule = new Schedule(dto.getNameWrite(), dto.getTodoTitle(), dto.getTodo()); // 2
        scheduleRepository.save(schedule); // 1. 서비스 단이네? 그러면 뭐야?
        return new ScheduleResponseDto(schedule.getId(), schedule.getNameWrite(), schedule.getTodoTitle(), schedule.getTodo(), schedule.getCreateTime(), schedule.getUpdateTime()); // 3.
    }

    public List<ScheduleResponseDto> findAll(){
        /*List<Schedule> schedules = scheduleRepository.findAll();

        List<ScheduleResponseDto> scheduleResponseDtos = new ArrayList<>();
        for(Schedule schedule:schedules){
            scheduleResponseDtos.add(new ScheduleResponseDto(schedule.getId(), schedule.getNameWrite(), schedule.getTodoTitle(), schedule.getTodo()));
        }
        return scheduleResponseDtos;*/
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    public ScheduleResponseDto findOne(Long id){
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id); // 1. id 대조해서 그 id에 맞는 데이터를 조회함.
        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getNameWrite(), findSchedule.getTodoTitle(), findSchedule.getTodo(), findSchedule.getCreateTime(), findSchedule.getUpdateTime());
        /*Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 스케줄이 없습니다.")
        );
        return new ScheduleResponseDto(schedule.getId(), schedule.getNameWrite(), schedule.getTodoTitle(), schedule.getTodo());*/
    }

    @Transactional
    public ScheduleResponseDto update(Long id, ScheduleRequestDto dto){
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionStatus.)
        );
        schedule.update(dto.getNameWrite(), dto.getTodoTitle(), dto.getTodo());
        return new ScheduleResponseDto(schedule.getId(), schedule.getNameWrite(), schedule.getTodoTitle(), schedule.getTodo(), schedule.getCreateTime(), schedule.getUpdateTime());
    }

    @Transactional
    public void delete(Long id){
        scheduleRepository.delete(scheduleRepository.findByIdOrElseThrow(id));

        /*if(!scheduleRepository.existsById(id)){
            throw new IllegalArgumentException("없는 id입니다.");
        }
        scheduleRepository.deleteById(id);*/
    }
}
