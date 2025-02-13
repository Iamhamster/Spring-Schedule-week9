package com.example.week9_schedule.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final String email;
    private final String name;

    public LoginResponseDto(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
