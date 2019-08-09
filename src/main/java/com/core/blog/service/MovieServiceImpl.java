package com.core.blog.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;


import com.core.blog.uitls.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zww on 2018-12-14.电影票房
 */
@Service
public class MovieServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    public Map<String, String> getApiContent(String Url) {
        Map<String, String> resultmap = new HashMap<>();
        Url = "https://box.maoyan.com/promovie/api/box/second.json";
        RestTemplate restTemplate = new RestTemplate();
        //headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Accept-Encoding", "gzip,deflate");
        headers.put("Cache-Control", "max-age=0");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9");
        headers.put("Connection", "keep-alive");
        headers.put("Host", "dianying.nuomi.com");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36");
        //params
        Map<String, String> params = new HashMap<>();
        try {
            String re = HttpClient.doGet(Url, headers, params);
            Map map = JSON.parseObject(re, Map.class);
            boolean result = (boolean) map.get("success");
            Map dataMap = JSON.parseObject(JSONUtils.toJSONString(map.get("data")), Map.class);
            if (result) {
                String dataListString = JSONUtils.toJSONString(dataMap.get("list"));
                ArrayList dataList = JSON.parseObject(dataListString, ArrayList.class);
                StringBuilder stringBuilder = new StringBuilder("<<<<<今日电影票房信息>>>>>");
                for (Object forecast : dataList) {
                    Map detailMap = JSON.parseObject(forecast.toString(), Map.class);
                    String movieName = detailMap.get("movieName").toString();
                    String attrValue1 = detailMap.get("releaseInfo").toString();//上映天数
                    String attrValue2 = detailMap.get("boxInfo").toString();//综合票房
                    String attrValue3 = detailMap.get("boxRate").toString();//票房占比
                    String attrValue4 = detailMap.get("avgSeatView").toString();//上坐率
                    int nameLeng = movieName.length();
                    String movie = getNeatMovieName(movieName, attrValue1, attrValue2, attrValue3, attrValue4, nameLeng);
                    stringBuilder.append(movie);
                }
                resultmap.put("email", stringBuilder.toString());
                return resultmap;
            }
        } catch (Exception e) {
            logger.error("获取电影信息异常>>>>>>>>>>>>>>>>>>>" + e.getMessage());
        }
        return resultmap;
    }

    public static String getNeatMovieName(String movieName, String attrValue1, String attrValue2,
                                          String attrValue3, String attrValue4, int length) {
        String sign = "&nbsp;";
        for (int i = 0; i < 6; i++) {
            sign += "&nbsp;";
        }
        return "<br/>电影名：" + movieName + sign + "上映时间" + ":" + attrValue1 + "<br/>"
                + "综合票房" + ":" + attrValue2 + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                "票房占比:" + attrValue3 + "<br/>" +
                "上坐率" + attrValue4 + "&nbsp;&nbsp;<br/><br/>";

    }
}
