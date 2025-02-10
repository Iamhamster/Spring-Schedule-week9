package com.example.week9_schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String name;
    private final String todoTitle;
    private final String todo;
    private final LocalDateTime createTime;
    private final LocalDateTime updateTime;

    public ScheduleResponseDto(Long id, String name, String todoTitle, String todo, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.name = name;
        this.todoTitle = todoTitle;
        this.todo = todo;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
