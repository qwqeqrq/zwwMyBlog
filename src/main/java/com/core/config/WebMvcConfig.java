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
        //2019年8月14日11:00:58 放行文件加入css放行 *.css解决拦截器 导致的页面多次访问后端获取css,css.map
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/**/*.css","/sys/login/**","/Email/unsubscribe/**");
    }
}
