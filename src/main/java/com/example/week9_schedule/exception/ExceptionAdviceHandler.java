package com.example.week9_schedule.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice //전역에 예외처리하는 어노테이션
public class ExceptionAdviceHandler {

    @ExceptionHandler(exception = CustomException.class)
    protected ResponseEntity<ErrorResponseDto> handleCustomException(CustomException e) {
        log.error("code : {} / message : {}", e.getMessage(), e.getExceptionStatus());
        return new ResponseEntity(new ErrorResponseDto(e.getExceptionStatus().getMessage(), e.getExceptionStatus().getErrorCode()), HttpStatus.valueOf(e.getExceptionStatus().getErrorCode()));
    }

//
//    @ExceptionHandler({MethodArgumentNotValidException.class})
//    protected ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        return new ResponseEntity<>(e.getBindingResult().getFieldErrors().get(0),
//                // .get(0).getField() + "가  "+ e.getBindingResult().getFieldErrors().get(0).getDefaultMessage(),
//                HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler({RuntimeException.class})
//    protected ResponseEntity<String> handlerEtcException(RuntimeException e) {
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//    }
}
