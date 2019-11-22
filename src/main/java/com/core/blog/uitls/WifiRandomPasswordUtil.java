package com.core.blog.uitls;


import java.util.Random;

public class WifiRandomPasswordUtil {
    /**
     * WiFi 8位 随机密码生成方法
     */
    public static void main(String[] args) {
        String password = "";
        int m;
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            m = random.nextInt(10 + 52);
            //写个方法将m转换成字母
            password += numberToString(m);//十个数字和大小写字母一共10+26*2=62个
        }
        System.out.println("您好本次给你推荐的WiFi安全密码是：" + password);
    }

    private static String numberToString(int n) {
        if (n < 10) { //0到9 直接返回
            return String.valueOf(n);
        }
        //利用 ASCII 码直接转换 a=97-> 10  z=122 -> 35 得到87的差值    A=65 ->36 z=90 ->61  得到 29的差值
        if (n <= 35) {
            n = n + 87;
            return String.valueOf((char) n);
        }
        if (n <= 61) {
            n = n + 29;
        }
        return String.valueOf((char) n);
    }
}
