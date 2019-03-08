package com.core.socket;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/9/28/00028
 * @Description:
 */
public class DouyuDto implements Serializable {

    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 主播
     */
    private String anchor;

    /**
     * 直播间
     */
    private String liveNumber;

    /**
     * 观看人数
     */
    private String vimNumber;

    /**
     * 直播日期（获取时间）
     */
    private Date liveTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public String getLiveNumber() {
        return liveNumber;
    }

    public void setLiveNumber(String liveNumber) {
        this.liveNumber = liveNumber;
    }

    public String getVimNumber() {
        return vimNumber;
    }

    public void setVimNumber(String vimNumber) {
        this.vimNumber = vimNumber;
    }

    public Date getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(Date liveTime) {
        this.liveTime = liveTime;
    }

    @Override
    public String toString() {
        return "DouyuDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", anchor='" + anchor + '\'' +
                ", liveNumber='" + liveNumber + '\'' +
                ", vimNumber='" + vimNumber + '\'' +
                ", liveTime=" + liveTime +
                '}';
    }
}
