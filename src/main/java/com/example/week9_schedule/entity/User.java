package com.example.week9_schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class User extends Base{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    @Column(nullable = false)
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

    public void updatePW(String pw){
        this.pw = pw;
    }
}
