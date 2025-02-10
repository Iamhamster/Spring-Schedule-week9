package com.example.week9_schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Week9ScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week9ScheduleApplication.class, args);
    }

}
