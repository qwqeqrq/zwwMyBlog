package com.core.blog.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/7/23
 * @Description:博客文章对象实体
 */
public class ArticleBean implements Serializable{
    //文章id
    private  Long id;

    //用户id
    private  Long userId;

    //类别id（1技术，2日记，3杂谈）
    private  Long lbId;

    //文章标题
    private  String title;

    //正文
    private  String text;

    //点击数
    private  Long clicks ;


    private Date addTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLbId() {
        return lbId;
    }

    public void setLbId(Long lbId) {
        this.lbId = lbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getClicks() {
        return clicks;
    }

    public void setClicks(Long clicks) {
        this.clicks = clicks;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
