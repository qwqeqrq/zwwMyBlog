package com.core.blog.uitls;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author lijm
 */
public class StringUtils {
    public static final char UNDERLINE = '_';
    static Pattern underLinePattern=Pattern.compile("_");


    public static String firstCharUpperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 驼峰格式字符串转换为下划线格式字符串
     *
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串
     *
     * @param param
     * @return
     */
    public static String underlineToCame(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串2
     *
     * @param param
     * @return
     */
    public static String underlineToCamel2(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        StringBuilder sb = new StringBuilder(param);
        Matcher mc = underLinePattern.matcher(param);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 如果字符串为 null 或 "" 或 "   " 返回true
     * 创建人：sage
     * 创建时间：2017/3/29 0029-13:43
     * @param str 传入的字符串参数
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        if(str == null || "".equals(str.trim()) || "null".equalsIgnoreCase(str)) {
            return true;
        }
        return false;
    }
    /**
     * 〈一句话简述isNotEmpty的作用〉
     * 该方法功能详细描述：判断单个字符串是否为空
     * @param str
     * @return boolean
     * 创建人：sage
     * 创建时间：2017/4/14-14:48
     * 修改人：ystwo
     * 修改时间：2017/4/14-14:48
     * 修改内容：
     */
    public static boolean isNotEmpty(String str) {

        if(str == null || "".equals(str.trim()) || "null".equalsIgnoreCase(str)) {
            return false;
        }
        return true;
    }
    /**
     * 〈一句话简述isNotEmpty的作用〉
     * 该方法功能详细描述：批量判断字符串是否为空
     * @param strs
     * @return boolean
     * 创建人：sage
     * 创建时间：2017/4/14-14:48
     * 修改人：ystwo
     * 修改时间：2017/4/14-14:48
     * 修改内容：
     */
    public static boolean isNotEmpty(String... strs) {
        List<String> list = Arrays.asList(strs);
        for (String s:list) {
            if(s == null || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
                return false;
            }
        }
        return true;
    }

}
