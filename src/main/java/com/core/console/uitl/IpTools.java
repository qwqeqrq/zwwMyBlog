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
     * @描述： 通过淘宝ip查询接口返回 ip所在地
     * @参数： [ip]
     * @返回值： java.lang.String
     * @创建人： zhangww
     * @创建时间： 2019-08-19
     * @修改人和其它信息：
     */
    public static String getIPCity(String ip) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", "ip.taobao.com");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
        headers.put("Cookie", "miid=103169097155626467; hng=CN%7Czh-CN%7CCNY%7C156; cna=kr9dFM/eck8CAW/I8hrg5XDM; " +
                "thw=cn; tracknick=%5Cu7D20%5Cu6708%5Cu58A8%5Cu7FBD%5Cu5929%5Cu7F57; tg=0; x=e%3D1%26p%3D*%26s%3D0%26c%3D0%" +
                "26f%3D0%26g%3D0%26t%3D0; t=dc7c7652d2180320170fff70cd7f33c7; lgc=%5Cu7D20%5Cu6708%5Cu58A8%5Cu7FBD%5Cu5929%5" +
                "Cu7F57; _cc_=Vq8l%2BKCLiw%3D%3D; UM_distinctid=16c0947eb56ffb-07da1e4c811ea4-36664c08-1fa400-16c0947eb5714c; u" +
                "c3=vt3=F8dBy3zZweUpjJzUrac%3D&id2=UoncgSAY2CTLQw%3D%3D&nk2=qHDPXGL6AQTzCklc&lg2=W5iHLLyFOGW7aA%3D%3D; isg=BH9_" +
                "AtThHuYIdRxzOf960sD6DlOlzNnntmwcIBFMGy51IJ-iGTRjVv2xYrB7eKt-; l=cBI4gW8evxhWBbzOBOCiqQIRhJbOSIRAguSRwRv6i_5d96" +
                "Tsl7bOk-CSHF96VjWdtGTB4082ve99-etu2zMqWXSpXUJ1.");
        Map<String, String> params = new HashMap<>();
        try {
            String ipResultStr = HttpClient.doGet("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip, headers, params);

            Map resultMap = JsonUtil.aliToMap(ipResultStr);
            IPCity ipCity = new IPCity();
            if (0 == (int) resultMap.get("code")) {
                Map dataMap = JsonUtil.aliToMap(resultMap.get("data").toString());
                ipCity.setCountry(dataMap.get("country").toString());
                ipCity.setCity(dataMap.get("city").toString());
                ipCity.setIsp(dataMap.get("isp").toString());
                return ipCity.toString(ip);
            }
            return ipCity.toString(ip);
        } catch (Exception e) {
            return "ip地理位置查询工具异常！速速查看";
        }
    }

}
