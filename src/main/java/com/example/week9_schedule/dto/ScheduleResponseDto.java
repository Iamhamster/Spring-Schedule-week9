package com.example.week9_schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String name;
    private final String todo;
    private final LocalDateTime createTime;
    private final LocalDateTime updateTime;

    public ScheduleResponseDto(Long id, String name, String todo, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.name = name;
        this.todo = todo;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
