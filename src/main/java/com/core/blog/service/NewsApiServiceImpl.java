package com.core.blog.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by zww on 2018-12-14.新闻api
 */
@Service
public class NewsApiServiceImpl implements GreatApiService {


    /**
     * @描述：网易新闻api
     * @参数：
     * @返回值：
     * @创建人： zhangww
     * @创建时间： 2018-12-14
     * @修改人和其它信息：
     */
    //@Override
    public String getApiContent(String ApiUrl) {
        ApiUrl = "https://www.apiopen.top/journalismApi";
        StringBuilder newStringBuffer = new StringBuilder("【一觉醒来发生了什么】<br/>");
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> resultMap = restTemplate.getForEntity(ApiUrl, Map.class).getBody();
        int code = (int) resultMap.get("code");
        if (code == 200) {
            Object newJsonObj = resultMap.get("data");
            String newMapStr = JSONUtils.toJSONString(newJsonObj);
            Map<String, List> newMap = JSON.parseObject(newMapStr, Map.class);
            List linkedHashMaps = newMap.get("toutiao");
            for (Object map : linkedHashMaps) {
                Map remap = JSON.parseObject(map.toString(), Map.class);
                String title = remap.get("title").toString();//标题
                String link = remap.get("link").toString();//连接
                String newString = "<a href=\"" + link + "\">" + title + "</a><br/>";
                newStringBuffer.append(newString);
            }
            return newStringBuffer.toString();
        } else {
            //发送邮件给自己，提示新闻接口异常
        }
        return null;
    }
}
