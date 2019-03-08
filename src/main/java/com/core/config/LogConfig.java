package com.core.config;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/7/20
 * @Description:
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 配置日志切面
 * Created by jianmin on 2017/11/20.
 * @author lijm
 */
@Aspect
@Component
@Configuration
public class LogConfig {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* com.bfdl.*.controller..*.*(..))")
    public void logPointcut(){
    }

    @Before("logPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        Long time=System.currentTimeMillis();
        Long threadId=Thread.currentThread().getId();
        String logId=Thread.currentThread().getId()+"_"+time;
        ThreadLocalLog.setLogId(logId);
        String log="logid:"+logId+" time:"+time+" "+joinPoint.getThis().toString()+" "+joinPoint.getSignature().getName()+" input param:"+ Arrays.asList(joinPoint.getArgs());
        logger.info(log);
    }

    @After("logPointcut()")
    public void doAfter(JoinPoint joinPoint) throws Throwable{
        Long time=System.currentTimeMillis();
        String log="logid:"+ThreadLocalLog.getLogId()+" time:"+time;
        logger.info(log);
    }
}
