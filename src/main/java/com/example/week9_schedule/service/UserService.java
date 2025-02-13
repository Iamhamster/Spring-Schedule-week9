package com.example.week9_schedule.service;

import com.example.week9_schedule.dto.*;
import com.example.week9_schedule.entity.Schedule;
import com.example.week9_schedule.entity.User;
import com.example.week9_schedule.exception.CustomException;
import com.example.week9_schedule.exception.ExceptionStatus;
import com.example.week9_schedule.repository.ScheduleRepository;
import com.example.week9_schedule.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public LoginResponseDto login(String email, String pw) {
        User byEmail = userRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ExceptionStatus.USER_IS_NOT_EXIST)
        );

        if(!byEmail.getPw().equals(pw)){
            throw new CustomException(ExceptionStatus.PASSWORD_WRONG);
        }
        return new LoginResponseDto(byEmail.getName(), byEmail.getEmail());
    }

    @Transactional
    public UserResponseDto signUp(UserRequestDto dto){    //save >> signUp 변경
        User user = userRepository.save(new User(dto.getName(), dto.getEmail(), dto.getPw()));  //회원가입: 사용자가 이름, 이메일, 비밀번호를 입력해서 회원가입

        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getCreateTime(), user.getCreateTime());  //회원가입: 가입이 잘 되었는지 이름, 이메일, 생성, 업데이트(생성과 같음)를 보여줌
    }

    @Transactional
    public List<UserResponseDto> findAll(){
        List<User> users = userRepository.findAll();    //모든 유저 확인: Repository에서 모든 유저에 대한 값을 불러와서 users변수에 넣어줌

        List<UserResponseDto> UserResponseDtos = new ArrayList<>();     //users는 리스트라서 바로 못 보여줌. UserResponseDto형식으로 변환할 수 있도록 새로 리스트 하나를 만들음
        for(User user:users){ //모든 유저 확인: 유저 한명씩 불러와서 add로 한 리스트로 만들어줌
            UserResponseDtos.add(new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getCreateTime(), user.getUpdateTime()));
        }

        return UserResponseDtos;//모든 유저를 보여줌.
    }

    public UserResponseDto findOne(Long id){
        Optional<User> optionalUser = userRepository.findById(id);  //한 유저 확인: id값으로 Repository에서 찾아서 한 유저의 값만 넣어줌.

        if(optionalUser.isEmpty()){ //찾고 있는 유저가 회원가입을 안해서 없는 유저인 경우, 에러가남.
            throw new CustomException(ExceptionStatus.USER_IS_NOT_EXIST);
        }

        User findUser = optionalUser.get(); //id를 기준으로 찾은 값들을 User형으로 바꿔서 넣어줌. 사용하기 쉽게
        return new UserResponseDto(findUser.getId(), findUser.getName(), findUser.getEmail(), findUser.getCreateTime(), findUser.getUpdateTime()); //한 유저 확인: 유저를 잘 찾았는지 id, 이름, 이메일, 생성, 업데이트를 보여줌

        /*User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 스케줄이 없습니다.")
        );
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getPw());*/
    }

    @Transactional
    public UserResponseDto updatePW(Long id, UserRequestDto dto){   //update >> updatePW 변경
        User findUser = userRepository.findByIdOrElseThrow(id); //id값으로 pw를 변경하고자 하는 유저를 찾음.

        /*if(!findUser.getPw().equals(dto.getPw())){  //현재의 패스워드를 사용자가 알고 있는지 물어봄.
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."); //현재의 패스워드를 사용자가 모르면 에러가남.(현재의 패스워드와 사용자가 알고 있는 패스워드와 다르면 에러)
        }*/

        //return findUser.updatePW(dto.getNewPW());
        findUser.updatePW(dto.getName(), dto.getEmail(), dto.getPw()); //새 패스워드를 입력받음.
        return new UserResponseDto(findUser.getId(), findUser.getName(), findUser.getEmail(), findUser.getCreateTime(), findUser.getUpdateTime()); //변경한 유저의 정보를 보여줌.(id, 이름, 이메일, 생성, 업데이트)
        /*User user = userRepository.findById(id).orElseThrow(      //UserResponseDto
                () -> new IllegalArgumentException("헤당 id에 맞는 스케줄이 없습니다.")
        );
        user.update(dto.getName(), dto.getEmail(), dto.getPw());
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getPw());*/
    }

    @Transactional
    public void delete(Long id){
        if(!userRepository.existsById(id)){ //id값으로 Repository에서 삭제하고자 하는 유저를 찾음.
            throw new CustomException(ExceptionStatus.ID_DOESNOT_EXIST);    //삭제하고자 하는 유저가 없는 경우, 에러가남.
        }
        userRepository.deleteById(id);  //id값이 같은 유저를 삭제
    }

}