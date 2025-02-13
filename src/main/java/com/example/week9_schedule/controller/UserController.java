package com.example.week9_schedule.controller;

import com.example.week9_schedule.dto.LoginResponseDto;
import com.example.week9_schedule.dto.UserRequestDto;
import com.example.week9_schedule.dto.UserResponseDto;
import com.example.week9_schedule.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @PostMapping("/signin")
    public LoginResponseDto login(HttpServletRequest request, @RequestBody UserRequestDto dto){
        LoginResponseDto login = userService.login(dto.getEmail(), dto.getPw());

        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
        }
        session.setAttribute("sessionKey", login.getEmail());

        return login;

    }

    @PostMapping("/signup") //save >> signUp 변경
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody UserRequestDto dto){
        return ResponseEntity.ok(userService.signUp(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findOne(@PathVariable Long id){
        return ResponseEntity.ok(userService.findOne(id));
    }

    @PutMapping("/{id}")    //update >> updatePW 변경
    public ResponseEntity<UserResponseDto> updatePW(@PathVariable Long id, @RequestBody UserRequestDto dto){     //UserResponseDto
        //UserService.updatePW(id, dto);

        //return new ResponseEntity<>(HttpStatus.OK);
        return ResponseEntity.ok(userService.updatePW(id, dto));    //pw를 변경하고자 하는 유저는 id이며, 변경하고자 하는 값과 기존값을 dto로 넘겨줌.
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }
}
