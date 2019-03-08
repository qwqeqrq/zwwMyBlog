package com.core.blog.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import org.omg.CORBA.NO_IMPLEMENT;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by zww on 2018-12-14.
 */
@Service
public class NovelServiceImpl implements GreatApiService {

    /**
     * 功能描述: 热门小说推荐
     *
     * @param: [Url]
     * @return: java.lang.String
     * @auther: ZHANGWEI
     * @date: 2018/12/14/00014 21:53
     */
    @Override
    public String getApiContent(String Url) {
        Url = "https://www.apiopen.top/novelApi";
        RestTemplate restTemplate = new RestTemplate();
        Map resultMap = restTemplate.getForEntity(Url, Map.class).getBody();
        int cede = (int) resultMap.get("code");
        if (cede == 200) {
            StringBuilder stringBuilder = new StringBuilder("<<<<<热门小说推荐>>>>>" + "<br/>");
            String dataStr = JSONUtils.toJSONString(resultMap.get("data"));
            List dataList = JSON.parseObject(dataStr, List.class);
            for (Object forecast : dataList) {
                Map dataMap = JSON.parseObject(forecast.toString(), Map.class);
                String bookname = dataMap.get("bookname").toString();
                String author_name = dataMap.get("author_name").toString();
                String topic = dataMap.get("topic").toString();
                String stat_name = dataMap.get("stat_name").toString();
                String class_name = dataMap.get("class_name").toString();
                String tag = dataMap.get("tag").toString();
                String novel = class_name + "&nbsp;" + "书名：" + bookname + "<br/>" + "作者：" + author_name + "<br/>" + "最新章节：" + topic + "<br/>" + "更新状态:" + stat_name + "<br/>  标签:" + tag + "<br/><br/>";
                stringBuilder.append(novel);
            }
            return stringBuilder.toString();
        }
        return "";
    }
}
