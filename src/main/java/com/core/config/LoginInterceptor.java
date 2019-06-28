package com.core.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zww on 2019-06-28.
 * <p>
 * 配置登录拦截器  实现原理 aop
 * 配置拦截器也很简单，Spring 为什么提供了基础类WebMvcConfigurerAdapter ，我们只需要重写 addInterceptors方法添加注册拦截器。
 * 实现自定义拦截器只需要3步：
 * 1、创建我们自己的拦截器类并实现 HandlerInterceptor 接口或继承HandlerInterceptorAdapter。
 * 2、创建一个Java类实现 WebMvcConfigurer
 * 3、实例化我们自定义的拦截器，然后将对像手动添加到拦截器链中（在addInterceptors方法中添加）。
 */
public class LoginInterceptor implements HandlerInterceptor {


    //方法执行之前干的执行的拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object userId = request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("/sys/login");
            return false;
        }

        return true;
    }

    //方法执行中的执行的拦截
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //方法执行之后的执行的拦截
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
