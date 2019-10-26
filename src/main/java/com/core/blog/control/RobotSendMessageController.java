package com.core.blog.control;


import com.alibaba.fastjson.JSON;
import com.core.blog.uitls.DingDingMessageBeanUiti;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.core.blog.service.*;

/**
 * @Auther: ZHANGWEI
 * @Date: 2019/3/8/00008   钉钉机器人控制类
 * @Description:
 */
@RestController
@RequestMapping(value = "dingding")
public class RobotSendMessageController {

    private static final Logger logger = LoggerFactory.getLogger(RobotSendMessageController.class);

    @Autowired
    private MovieServiceImpl movieServiceImplMovieService;
    @Autowired
    private JokeiServiceImpl jokeiService;

    @Autowired
    private DingDingMessageBeanUiti dingDingMessageBeanUiti;

    @Scheduled(cron = "0 20 08 ? * *")
    public String robotSendMessage() {
        //String movie = movieServiceImplMovieService.getApiContent("").get("dingding");
        String joke =  jokeiService.getApiContentByDingDing("");
        List<String> atList = new ArrayList<>();
        try {
            //发送电影票房
            //dingDingMessageBeanUiti.sendMessageUtil(false, movie, atList);
            //笑话
            dingDingMessageBeanUiti.sendMessageUtil(false, joke, atList);
        } catch (Exception e) {
            logger.error("钉钉笑话异常请速速查看"+JSON.toJSONString(e));
        }
        return "钉钉程序执行完毕";
    }

}
