package com.core.config;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/7/20
 * @Description:
 */
public class ThreadLocalLog {
    static ThreadLocal<String> threadLocal=new ThreadLocal<String>();

    public static void setLogId(String value){
        threadLocal.set(value);
    }

    public static String getLogId(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }
}
