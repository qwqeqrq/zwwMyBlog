package com.core.blog.service;

import com.core.blog.po.ReplyBean;

import java.util.List;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/8/6/00006
 * @Description:
 */
public interface ReplyService  {

    //更新回复
    Integer updateReply (ReplyBean replyBean);

    //添加回复
    Integer addRepiy(ReplyBean replyBean);

    //展示回复
    List<ReplyBean> getReply(ReplyBean replyBean);

    //删除
    Integer deleteByPrimaryKey(Integer id);
}
