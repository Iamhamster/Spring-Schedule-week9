package com.example.week9_schedule.dto;

import com.example.week9_schedule.entity.Schedule;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String nameWrite;
    private final String todoTitle;
    private final String todo;
    private final LocalDateTime createTime;
    private final LocalDateTime updateTime;

    public ScheduleResponseDto(Long id, String nameWrite, String todoTitle, String todo, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.nameWrite = nameWrite;
        this.todoTitle = todoTitle;
        this.todo = todo;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public  static ScheduleResponseDto toDto(Schedule schedule){
        return new ScheduleResponseDto(schedule.getId(), schedule.getNameWrite(), schedule.getTodoTitle(), schedule.getTodo(), schedule.getCreateTime(), schedule.getUpdateTime());
    }
}
