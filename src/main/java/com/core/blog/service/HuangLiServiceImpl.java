package com.core.blog.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by zww on 2018-12-14.老黄历
 */
@Service
public class HuangLiServiceImpl implements GreatApiService {
    private static final Logger logger = LoggerFactory.getLogger(HuangLiServiceImpl.class);

    @Override
    public String getApiContent(String Url) {
        Url = "http://api.djapi.cn/wannianli/get?token=297bf40efb7579c569f7ceb814b3d04d";
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map resultMap = restTemplate.getForEntity(Url, Map.class).getBody();
            int cede = (int) resultMap.get("ErrorCode");
            if (cede == 0) {
                String jsonStr = JSONUtils.toJSONString(resultMap.get("Result"));
                Map dataMap = JSON.parseObject(jsonStr, Map.class);
                String week = dataMap.get("week").toString();
                String nongli = dataMap.get("nongli").toString();
                String nianci = dataMap.get("nianci").toString();
                String Do = dataMap.get("do").toString();
                String nodo = dataMap.get("nodo").toString();
                String jieqi = dataMap.get("jieqi").toString();
                String shengxiao = dataMap.get("shengxiao").toString();
                String runnian = dataMap.get("runnian").toString();
                String huangli = "今日黄历:" + "<br/>"
                        + "星期:" + week + "<br/>"
                        + "农历:" + nongli + "<br/>"
                        + "年次:" + nianci + "<br/>"
                        + "宜:" + Do + "<br/>"
                        + "忌:" + nodo + "<br/>"
                        + "节气:" + jieqi + "<br/>"
                        + "生肖:" + shengxiao + "<br/>"
                        + "是否闰年:" + runnian + "<br/>";
                return huangli;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "";
    }
}
