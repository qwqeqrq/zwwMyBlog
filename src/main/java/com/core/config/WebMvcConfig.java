package com.core.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by zww on 2019-06-28.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    //实现添加拦截器的方法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //TODO  addPathPatterns方法添加的路径都要进行拦截，excludePathPatterns这是放行的路径不用检测登录状态
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/sys/login/**","/Email/unsubscribe/**").excludePathPatterns("/");
    }
}
