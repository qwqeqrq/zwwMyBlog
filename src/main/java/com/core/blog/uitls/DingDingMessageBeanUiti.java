package com.core.blog.uitls;

import com.core.blog.po.DingDingAtInfo;
import com.core.blog.po.DingDingMessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by zww on 2019-03-08. 钉钉消息工具类
 */
@Component
public class DingDingMessageBeanUiti {

    @Autowired
    private RestTemplate restTemplate;


    @Value("${dingDingRobotUrl}")
    private String dingDingRobotUrl;
    @Value("${telphone1}")
    private String telphone1;
    @Value("${telphone2}")
    private String telphone2;


    /**
     * @描述： 钉钉机器人发送消息体
     * @参数： [atList 需要被@的电话 , atAll 是否@所有人 ,message 发送消息内容]
     * @返回值： com.qsmx.zww.po.DingDingMessageInfo
     * @创建人： zhangww
     * @创建时间： 2019-03-08
     * @修改人和其它信息：
     */
    //得到消息发送体
    public static DingDingMessageInfo getDingDingMessageBean(List<String> atList, boolean atAll, String message) {
        try {
            DingDingMessageInfo dingDingMessageInfo = new DingDingMessageInfo();//消息体BEAN
            DingDingAtInfo dingDingAtInfo = new DingDingAtInfo();//设置At 对象
            List<String> list = new ArrayList<>();//需要被@的联系人
            list.addAll(atList);
            dingDingAtInfo.setAtMobiles(list);
            if (atAll) {
                dingDingAtInfo.setAtAll(atAll);
            }
            Map<String, String> messageMessage = new HashMap<>();
            messageMessage.put("content", message);
            dingDingMessageInfo.setMsgtype("text");
            dingDingMessageInfo.setText(messageMessage);
            dingDingMessageInfo.setAt(dingDingAtInfo);
            return dingDingMessageInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @描述： 钉钉机器人发送消息方法
     * @参数： [dingDingMessageInfo 参数类, url 发送的群的连接地址]
     * @返回值： java.lang.Boolean
     * @创建人： zhangww
     * @创建时间： 2019-03-08
     * @修改人和其它信息：
     */
    public Boolean sendMessageByDingDingRobot(@RequestBody DingDingMessageInfo dingDingMessageInfo, String url) {
        try {
            Map<String, Object> resultMap = restTemplate.postForObject(url, dingDingMessageInfo, Map.class);
            if ("ok".equals(Optional.ofNullable(resultMap.get("errmsg")).orElse("false").toString()) &&
                    0 == Integer.valueOf(Optional.ofNullable(resultMap.get("errcode")).orElse("1001").toString())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @描述： 通过钉钉机器人给群里发起信息
     * @参数： [boolean atAll 是否@所有人, String message 发送消息内容] List<String> atList 需要被@的手机列表
     * @返回值： java.lang.String
     * @创建人： zhangww
     * @创建时间： 2019-03-08
     * @修改人和其它信息：
     */
    public Boolean sendMessageUtil(boolean atAll, String message, List<String> atList) throws Exception {
        atList.add(telphone1);
        atList.add(telphone2);
        DingDingMessageInfo dingDingMessageInfo = DingDingMessageBeanUiti.getDingDingMessageBean(atList, false, message);
        if (dingDingMessageInfo == null) {
            return false;
        }
        if (sendMessageByDingDingRobot(dingDingMessageInfo, dingDingRobotUrl)) {
            return true;
        }
        return false;
    }
}