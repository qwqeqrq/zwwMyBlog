package com.core.blog.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by zww on 2018-12-14.新闻api
 */
@Service
public class NewsApiServiceImpl implements GreatApiService {
    private static final Logger logger = LoggerFactory.getLogger(NewsApiServiceImpl.class);

    /**
     * @描述：网易新闻api
     * @参数：
     * @返回值：
     * @创建人： zhangww
     * @创建时间： 2018-12-14 2019年11月11日09:15:36 修改了url
     * @修改人和其它信息：
     */
    //@Override
    public String getApiContent(String ApiUrl) {
        ApiUrl = "https://api.apiopen.top/getWangYiNews";
        StringBuilder newStringBuffer = new StringBuilder("【一觉醒来发生了什么】<br/>");
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> resultMap = restTemplate.getForEntity(ApiUrl, Map.class).getBody();
            int code = (int) resultMap.get("code");
            if (code == 200) {
                List linkedHashMaps = (List) resultMap.get("result");
                for (Object map : linkedHashMaps) {
                    Map remap = (Map) map;
                    String title = remap.get("title").toString();//标题
                    String link = remap.get("path").toString();//连接
                    String newString = "<a href=\"" + link + "\">" + title + "</a><br/>";
                    newStringBuffer.append(newString);
                }
                return newStringBuffer.toString();
            } else {
                //发送邮件给自己，提示新闻接口异常
            }
        } catch (Exception e) {
            logger.error("获取新闻异常！！>>>>>>>>>>>>>>>>>>>>" + e.getMessage());
        }
        return null;
    }
}
