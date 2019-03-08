package com.core.blog.po;


import java.io.Serializable;
import java.util.List;

/**
 * Created by zww on 2019-03-08. 钉钉@人对象
 */
public class DingDingAtInfo implements Serializable {
    //@人的列表
    private List<String> atMobiles;
    //是否@所有人
    private boolean isAtAll;

    public List<String> getAtMobiles() {
        return atMobiles;
    }

    public void setAtMobiles(List<String> atMobiles) {
        this.atMobiles = atMobiles;
    }

    public boolean getIsAtAll() {
        return isAtAll;
    }

    public void setAtAll(boolean isAtAll) {
        isAtAll = isAtAll;
    }
}