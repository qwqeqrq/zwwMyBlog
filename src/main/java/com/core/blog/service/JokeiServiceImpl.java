package com.core.blog.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by zww on 2018-12-14.每日笑话
 */
@Service
public class JokeiServiceImpl implements GreatApiService {
    private static final Logger logger = LoggerFactory.getLogger(JokeiServiceImpl.class);

    @Override
    public String getApiContent(String Url) {
        Url = "http://api.djapi.cn/joke/get?token=297bf40efb7579c569f7ceb814b3d04d";
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map resultMap = restTemplate.getForEntity(Url, Map.class).getBody();
            int cede = (int) resultMap.get("ErrorCode");
            if (cede == 0) {
                String jsonStr = JSONUtils.toJSONString(resultMap.get("Result"));
                Map dataMap = JSON.parseObject(jsonStr, Map.class);
                String jokeContent = dataMap.get("content").toString();
                // StringBuilder stringBuilder = new StringBuilder(jokeContent);
                jokeContent = jokeContent.replaceAll("，", "<br/>");
                jokeContent = jokeContent.replaceAll("。", "<br/>");
                String joke = "今日笑话（每天笑一下）:" + "<br/>" + jokeContent + "<br/><br/><br/><br/>" +
                        "<a href=\"" + "http://47.104.175.49:8088/Email/unsubscribe" + "\">" + "退订邮件" + "</a><br/>";
                return joke;
            }
        } catch (Exception e) {
            logger.error("获取笑话异常>>>>>>>>>>>>>>>>>" + e.getMessage());
        }
        return "";
    }

    public String getApiContentByDingDing(String Url) {
        Url = "http://api.djapi.cn/joke/get?token=297bf40efb7579c569f7ceb814b3d04d";
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map resultMap = restTemplate.getForEntity(Url, Map.class).getBody();
            int cede = (int) resultMap.get("ErrorCode");
            if (cede == 0) {
                String jsonStr = JSONUtils.toJSONString(resultMap.get("Result"));
                Map dataMap = JSON.parseObject(jsonStr, Map.class);
                String jokeContent = dataMap.get("content").toString();
                // StringBuilder stringBuilder = new StringBuilder(jokeContent);
           /* jokeContent = jokeContent.replaceAll("，", "\n");
            jokeContent = jokeContent.replaceAll("。", "\n");*/
                String joke = "今日笑话（每天笑一下）:" + "\n" + jokeContent;
                return joke;
            }
        } catch (Exception e) {
            logger.error("获取笑话异常57行>>>>>>>>>>>>>>>>>>>>>>" + e.getMessage());
        }
        return "";
    }
}
