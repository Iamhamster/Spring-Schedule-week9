package com.example.week9_schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private final Long id;
    private final String name;
    private final String email;
    private final LocalDateTime createTime;
    private final LocalDateTime updateTime;

    public UserResponseDto(Long id, String name, String email, LocalDateTime createTime, LocalDateTime updateTime) {    //signUp
        this.id = id;
        this.name = name;
        this.email = email;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
