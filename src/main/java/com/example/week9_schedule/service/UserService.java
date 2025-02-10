package com.example.week9_schedule.service;

import com.example.week9_schedule.dto.ScheduleRequestDto;
import com.example.week9_schedule.dto.ScheduleResponseDto;
import com.example.week9_schedule.dto.UserRequestDto;
import com.example.week9_schedule.dto.UserResponseDto;
import com.example.week9_schedule.entity.Schedule;
import com.example.week9_schedule.entity.User;
import com.example.week9_schedule.repository.ScheduleRepository;
import com.example.week9_schedule.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto save(UserRequestDto dto){
        User user = userRepository.save(new User(dto.getName(), dto.getEmail(), dto.getPw(), LocalDateTime.now(), LocalDateTime.now()));

        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getPw(), user.getCreateTime(), user.getUpdateTime());
    }

    public List<UserResponseDto> findAll(){
        List<User> users = userRepository.findAll();

        List<UserResponseDto> UserResponseDtos = new ArrayList<>();
        for(User user:users){
            UserResponseDtos.add(new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getPw(), user.getCreateTime(), user.getUpdateTime()));
        }

        return UserResponseDtos;
    }

    public UserResponseDto findOne(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 스케줄이 없습니다.")
        );
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getPw(), user.getCreateTime(), user.getUpdateTime());
    }

    @Transactional
    public UserResponseDto update(Long id, UserRequestDto dto){
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("헤당 id에 맞는 스케줄이 없습니다.")
        );
        user.update(dto.getName(), dto.getEmail(), dto.getPw(), dto.getCerateTime(), LocalDateTime.now());
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getPw(), user.getCreateTime(), user.getUpdateTime());
    }

    @Transactional
    public void delete(Long id){
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("없는 id입니다.");
        }
        userRepository.deleteById(id);
    }
}