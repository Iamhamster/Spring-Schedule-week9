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

    private String nameWrite;

    @Column(nullable = false)
    private String todoTitle;

    @Column(columnDefinition = "logtext")
    private String todo;

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

    public void serUser(User user){
        this.user = user;
    }
}
