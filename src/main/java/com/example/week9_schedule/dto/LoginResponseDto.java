package com.example.week9_schedule.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final String name;
    private final String email;

    public LoginResponseDto(String name,String email) {
        this.name = name;
        this.email = email;
    }
}
