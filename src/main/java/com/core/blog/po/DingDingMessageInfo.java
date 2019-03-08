package com.core.blog.po;


import java.io.Serializable;
import java.util.Map;

/**
 * Created by zww on 2019-03-08. 钉钉机器人发送消息格式实体
 */
public class DingDingMessageInfo implements Serializable {
    //消息类型
    private String msgtype;
    //消息主体
    private Map<String, String> text;
    //@对象
    private DingDingAtInfo at;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Map<String, String> getText() {
        return text;
    }

    public void setText(Map<String, String> text) {
        this.text = text;
    }

    public DingDingAtInfo getAt() {
        return at;
    }

    public void setAt(DingDingAtInfo at) {
        this.at = at;
    }
}