package com.core.console.uitl;

import com.core.blog.uitls.HttpClient;
import com.core.blog.uitls.JsonUtil;
import com.core.console.po.IPCity;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zww on 2019-07-03.
 */
public class IpTools {

    /**
     * 13      * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 14 *
     * 16      * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 17      * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * 18      *
     * 19      * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 20      * 192.168.1.100
     * 21      *
     * 22      * 用户真实IP为： 192.168.1.110
     * 23      *
     * 24      * @param request
     * 25      * @return
     * 26
     */

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;

    }

    /**
     * @描述： 通过ip查询接口返回 ip所在地
     * @参数： [ip]
     * @返回值： java.lang.String
     * @创建人： zhangww
     * @创建时间： 2019-08-19
     * @修改人和其它信息：
     */
    public static String getIPCity(String ip) {
        try {
            String ipAdress = HttpClient.doGet("http://freeapi.ipip.net/" + ip);
            return ip+":"+ipAdress;
        } catch (Exception e) {
            return "ip地理位置查询工具异常！速速查看";
        }
    }
}
