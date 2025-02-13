package com.example.week9_schedule.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionStatus {
    PASSWORDS_DO_NOT_MATCH(401,"비밀번호가 일치 하지 않습니다."),
    INVALID_TOKEN(401,"유효하지 않은 토큰입니다."),
    AUTHORIZATION_EXCEPTION(403,"접근할 수 있는 권한이 없습니다."),
    POST_IS_EMPTY(404,"해당 게시글이 존재 하지 않습니다."),
    COMMENT_IS_EMPTY(404,"해당 댓글이 존재 하지 않습니다."),
    USER_IS_NOT_EXIST(404,"사용자가 존재 하지 않습니다."),
    REQUEST_IS_EMPTY(404,"요청이 존재하지 않습니다."),
    PAGE_IS_NOT_EXIST(404,"요청하신 페이지 내역이 존재하지 않습니다."),
    USERNAME_IS_EXIST(409,"이미 등록된 username입니다."),
    ID_DOESNOT_EXIST(401, "아이디가 없습니다."),
    PASSWORD_WRONG(401, "비밀번호가 틀렸어요."),
    SCHEDULE_DOES_NOT_EXIST(401,"스케쥴이 없어요.");


    private int errorCode;
    private String message;
}
