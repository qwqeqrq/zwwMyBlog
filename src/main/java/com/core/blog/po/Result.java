package com.core.blog.po;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/7/20
 * @Description:接口返回对象
 */
public class Result<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;
    private static Map<Integer, String> errors = new HashMap();

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Map<Integer, String> getErrors() {
        return errors;
    }

    public static void setErrors(Map<Integer, String> errors) {
        Result.errors = errors;
    }

    static {
        errors.put(Integer.valueOf(-1), "系统错误，请稍后重试");
        errors.put(Integer.valueOf(0), "操作成功");
        errors.put(Integer.valueOf(1), "重复请求");
        errors.put(Integer.valueOf(2), "手机号错误");
        errors.put(Integer.valueOf(4), "格式错误，正确格式{P}");
        errors.put(Integer.valueOf(8), "用户不存在");
        errors.put(Integer.valueOf(16), "用户已存在");
        errors.put(Integer.valueOf(401), "请求需要用户验证");
        errors.put(Integer.valueOf(503), "服务不可用");
    }
}
