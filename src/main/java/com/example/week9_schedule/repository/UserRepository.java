package com.example.week9_schedule.repository;

import com.example.week9_schedule.entity.Schedule;
import com.example.week9_schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
