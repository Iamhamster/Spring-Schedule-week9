package com.example.week9_schedule.dto;

import com.example.week9_schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String nameWrite;
    private final String todoTitle;
    private final String todo;

    public ScheduleResponseDto(Long id, String nameWrite, String todoTitle, String todo) {
        this.id = id;
        this.nameWrite = nameWrite;
        this.todoTitle = todoTitle;
        this.todo = todo;
    }

    public  static ScheduleResponseDto toDto(Schedule schedule){
        return new ScheduleResponseDto(schedule.getId(), schedule.getNameWrite(), schedule.getTodoTitle(), schedule.getTodo());
    }

    public ScheduleResponseDto(String nameWrite, String todoTitle, String todo) {
        this.nameWrite = nameWrite;
        this.todoTitle = todoTitle;
        this.todo = todo;
    }
}
