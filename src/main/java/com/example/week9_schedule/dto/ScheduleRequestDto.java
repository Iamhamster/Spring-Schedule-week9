package com.example.week9_schedule.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class ScheduleRequestDto {
    private String nameWrite;

    @Range(max=10)
    private String todoTitle;

    private String todo;
}
