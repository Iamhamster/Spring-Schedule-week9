package com.example.week9_schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Schedule extends Base{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nameWrite; //작성자명

    @Column(nullable = false)
    private String todoTitle; // 일정 제목

    @Column(columnDefinition = "logtext")
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

    public void setUser(User user){
        this.user = user;
    }
}
