package com.example.week9_schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String pw;

    public User(String name, String email, String pw){
        this.name = name;
        this.email = email;
        this.pw = pw;
    }

    public void update(String name, String email, String pw){
        this.name = name;
        this.email = email;
        this.pw = pw;
    }
}
