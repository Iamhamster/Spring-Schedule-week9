package com.example.week9_schedule.config;

import com.example.week9_schedule.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean loginFilter(){//로그인 필터 등록
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter()); // 필터 선언
        filterRegistrationBean.setOrder(1); // 필터의 순서 -> 이거는 1번째로 지나간다.
        filterRegistrationBean.addUrlPatterns("/*"); // 어떤 거를 거를건데?? -> 전체

        return filterRegistrationBean;
    }
}
