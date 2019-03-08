package com.core.blog.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;


import com.core.blog.uitls.HttpClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zww on 2018-12-14.电影票房
 */
@Service
public class MovieServiceImpl  {

    public Map<String,String> getApiContent(String Url) {
        Map<String,String> resultmap =new HashMap<>();
        Url = "http://dianying.nuomi.com/movie/boxrefresh";
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
            String realStr = JSONUtils.toJSONString(map.get("real"));
            Map realMap = JSON.parseObject(realStr, Map.class);
            int cede = (int) realMap.get("errorNo");
            if (cede == 0) {
                StringBuilder stringBuilder = new StringBuilder("<<<<<最近电影票房信息>>>>>");
                StringBuilder stringBuilder2 = new StringBuilder("<<<<<最近电影票房信息>>>>>");
                String dataStr = JSONUtils.toJSONString(realMap.get("data"));
                Map dataMap = JSON.parseObject(dataStr, Map.class);
                String detailStr = JSONUtils.toJSONString(dataMap.get("detail"));
                List dataList = JSON.parseObject(detailStr, List.class);
                for (Object forecast : dataList) {
                    Map detailMap = JSON.parseObject(forecast.toString(), Map.class);
                    String movieName = detailMap.get("movieName").toString();
                    Map author_name = JSON.parseObject(JSONUtils.toJSONString(detailMap.get("attribute")), Map.class);
                    Map map1 = JSON.parseObject(JSONUtils.toJSONString(author_name.get("1")), Map.class);
                    Map map2 = JSON.parseObject(JSONUtils.toJSONString(author_name.get("2")), Map.class);
                    Map map3 = JSON.parseObject(JSONUtils.toJSONString(author_name.get("3")), Map.class);
                    Map map4 = JSON.parseObject(JSONUtils.toJSONString(author_name.get("4")), Map.class);
                    String attrName1 = map1.get("attrName").toString();
                    String attrValue1 = map1.get("attrValue").toString();
                    String attrName2 = map2.get("attrName").toString();
                    String attrValue2 = map2.get("attrValue").toString();
                    String attrName3 = map3.get("attrName").toString();
                    String attrValue3 = map3.get("attrValue").toString();
                    String attrName4 = map4.get("attrName").toString();
                    String attrValue4 = map4.get("attrValue").toString();
                    int nameLeng = movieName.length();
                    String movie = getNeatMovieName(movieName, attrName1, attrValue1, attrName2, attrValue2, attrName3, attrValue3, attrName4, attrValue4, nameLeng);
                    String movie2 = getDingDingNeatMovieName(movieName, attrName1, attrValue1, attrName2, attrValue2, attrName3, attrValue3, attrName4, attrValue4, nameLeng);
                    stringBuilder.append(movie);
                    stringBuilder2.append(movie2);
                }
                resultmap.put("email",stringBuilder.toString());
                resultmap.put("dingding",stringBuilder2.toString());
                return resultmap;
            }
        } catch (Exception e) {

        }
        return resultmap;
    }

    public static String getNeatMovieName(String movieName, String attrName1, String attrValue1,
                                          String attrName2, String attrValue2, String attrName3,
                                          String attrValue3, String attrName4, String attrValue4,
                                          int length) {
        String sign = "&nbsp;";
        for (int i = 0; i < 6; i++) {
            sign += "&nbsp;";
        }
        return "<br/>电影名：" + movieName + sign + attrName1 + ":" + attrValue1 + "<br/>"
                + attrName2 + ":" + attrValue2 + "&nbsp;&nbsp;&nbsp;"
                + attrName3 + ":" + attrValue3 + "<br/>"
                + attrName4 + ":" + attrValue4 + "<br/><br/>";

    }

    public static String getDingDingNeatMovieName(String movieName, String attrName1, String attrValue1,
                                          String attrName2, String attrValue2, String attrName3,
                                          String attrValue3, String attrName4, String attrValue4,
                                          int length) {
        String sign = "\t";
        for (int i = 0; i < 2; i++) {
            sign += "\t";
        }
        return "\n电影名：" + movieName + sign + attrName1 + ":" + attrValue1 + "\n"
                + attrName2 + ":" + attrValue2 + "\t"
                + attrName3 + ":" + attrValue3 + "\n"
                + attrName4 + ":" + attrValue4 + "\n";

    }

}
