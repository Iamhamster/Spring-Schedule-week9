package com.example.week9_schedule.service;

import com.example.week9_schedule.dto.ScheduleRequestDto;
import com.example.week9_schedule.dto.ScheduleResponseDto;
import com.example.week9_schedule.entity.Schedule;
import com.example.week9_schedule.entity.User;
import com.example.week9_schedule.repository.ScheduleRepository;
import com.example.week9_schedule.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto save(ScheduleRequestDto dto){
        User findUser = userRepository.findUserByNameOrElseThrow(dto.getNameWrite());
        Schedule schedule = scheduleRepository.save(new Schedule(dto.getNameWrite(), dto.getTodoTitle(), dto.getTodo()));
        schedule.setUser(findUser);
        return new ScheduleResponseDto(schedule.getId(), schedule.getNameWrite(), schedule.getTodoTitle(), schedule.getTodo());
    }

    public List<ScheduleResponseDto> findAll(){
        /*List<Schedule> schedules = scheduleRepository.findAll();

        List<ScheduleResponseDto> scheduleResponseDtos = new ArrayList<>();
        for(Schedule schedule:schedules){
            scheduleResponseDtos.add(new ScheduleResponseDto(schedule.getId(), schedule.getNameWrite(), schedule.getTodoTitle(), schedule.getTodo()));
        }
        return scheduleResponseDtos;*/
        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::toDto).toList();
    }

    public ScheduleResponseDto findOne(Long id){
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        User writer = findSchedule.getUser();
        return new ScheduleResponseDto(writer.getName(), findSchedule.getTodoTitle(), findSchedule.getTodo());
        /*Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 스케줄이 없습니다.")
        );
        return new ScheduleResponseDto(schedule.getId(), schedule.getNameWrite(), schedule.getTodoTitle(), schedule.getTodo());*/
    }

    @Transactional
    public ScheduleResponseDto update(Long id, ScheduleRequestDto dto){
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("헤당 id에 맞는 스케줄이 없습니다.")
        );
        schedule.update(dto.getNameWrite(), dto.getTodoTitle(), dto.getTodo());
        return new ScheduleResponseDto(schedule.getId(), schedule.getNameWrite(), schedule.getTodoTitle(), schedule.getTodo());
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
