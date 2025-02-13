package com.example.week9_schedule.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/users/signup", "/users/signin"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI(); // 다운캐스팅중이다.

        HttpServletResponse httpResponse = (HttpServletResponse) response; // 다운캐스팅

        log.info("로그인 필터 로직 실행");

        //WHITE LIST에 포함된 경우 true -> !true -> false
        if(!isWiteList(requestURI)){    //isWhiteList : WHITE_LIST를 제외한 모든 경우에 인증체크를 하도록
            HttpSession session = httpRequest.getSession(false);

            if(session==null || session.getAttribute("seesionKey")==null){
                throw new RuntimeException("로그인 해주세요.");
            }

            //로그인 성공 로직
            log.info("로그인에 성공했습니다.");
        }

        // 1번 경우 : WHITE LIST에 등록된 URL 요청이라면 chain.doFilter() 호출
        //2번 경우 : WHITE LIST가 아닌 경우 위 필터로직을 통과 후에 chain.doFilter() 다음 필터나 Servlet 호출한다.
        //다음 필터가 없으면 Servlet -> Controller, 다음 필터가 있으면 다음 Filter 호출한다.
        chain.doFilter(request, response);
    }

    private boolean isWiteList(String requestURI){
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
