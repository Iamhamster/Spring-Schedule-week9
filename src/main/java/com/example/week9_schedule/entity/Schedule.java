package com.example.week9_schedule.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Schedule {
    @Id
    private Long id;

    private String name;

    private String todo;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime updateTime;

    public Schedule(String name, String todo, LocalDateTime createTime, LocalDateTime updateTime){
        this.name = name;
        this.todo = todo;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public void update(String name, String todo, LocalDateTime updateTime){
        this.name = name;
        this.todo = todo;
        this.updateTime = updateTime;
    }
}
