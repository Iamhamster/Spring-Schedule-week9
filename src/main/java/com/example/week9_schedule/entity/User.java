package com.example.week9_schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String pw;

    public User(String name, String pw){
        this.name = name;
        this.pw = pw;
    }

    public void update(String name, String pw){
        this.name = name;
        this.pw = pw;
    }
}
