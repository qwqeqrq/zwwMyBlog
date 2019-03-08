package com.core.blog.service;

import com.core.blog.dao.replydao.ReplyDao;
import com.core.blog.po.ReplyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/8/6/00006
 * @Description:
 */
@Service
public class ReplyServiceImp implements ReplyService {
    @Autowired
    ReplyDao replyDao;

    //更新回复
    @Override
    public Integer updateReply(ReplyBean replyBean) {
        return replyDao.updateReply(replyBean);
    }

    //新增回复
    @Override
    public Integer addRepiy(ReplyBean replyBean) {
        return replyDao.addRepiy(replyBean);
    }

    //查询回复
    @Override
    public List<ReplyBean> getReply(ReplyBean replyBean) {
        return replyDao.getReply(replyBean);
    }

    //根据id删除回复
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return replyDao.deleteByPrimaryKey(id);
    }
}
