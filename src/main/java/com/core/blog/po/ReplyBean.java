package com.core.blog.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/7/23
 * @Description:文章评论对象
 */
public class ReplyBean implements Serializable {
    private  Long id;

    //文章id
    private  Long articleId;

    //评论人id
    private  Long pingLunRenId;

    //评论内容
    private  String pingLunNeiRong;

    //回复内容
    private  String huiFuNeiRong;


    private Date addTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getPingLunRenId() {
        return pingLunRenId;
    }

    public void setPingLunRenId(Long pingLunRenId) {
        this.pingLunRenId = pingLunRenId;
    }

    public String getPingLunNeiRong() {
        return pingLunNeiRong;
    }

    public void setPingLunNeiRong(String pingLunNeiRong) {
        this.pingLunNeiRong = pingLunNeiRong;
    }

    public String getHuiFuNeiRong() {
        return huiFuNeiRong;
    }

    public void setHuiFuNeiRong(String huiFuNeiRong) {
        this.huiFuNeiRong = huiFuNeiRong;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
