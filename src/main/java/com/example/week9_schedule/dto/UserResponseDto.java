package com.example.week9_schedule.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {
    private final String name;
    private final String email;
    private final String pw;

    public UserResponseDto(String name, String email, String pw) {
        this.name = name;
        this.email = email;
        this.pw = pw;
    }
}
