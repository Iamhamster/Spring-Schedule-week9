package com.example.week9_schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor

public class Schedule extends Base{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nameWrite; //작성자명

    @Column(nullable = false)
    private String todoTitle; // 일정 제목

    @Column(columnDefinition = "longtext")
    private String todo; // 일정 내용

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Schedule(String nameWrite, String todoTitle, String todo){
        this.nameWrite = nameWrite;
        this.todoTitle = todoTitle;
        this.todo = todo;
    }

    public void update(String nameWrite, String todoTitle, String todo){
        this.nameWrite = nameWrite;
        this.todoTitle = todoTitle;
        this.todo = todo;
    }

}
