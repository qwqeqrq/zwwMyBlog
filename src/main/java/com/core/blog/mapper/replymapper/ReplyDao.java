package com.core.blog.mapper.replymapper;

import com.core.blog.po.ReplyBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/7/23
 * @Description:
 */
@Mapper
public interface ReplyDao {

    //更新回复
    Integer updateReply (@Param("reply") ReplyBean replyBean);

    //添加回复
    Integer addRepiy(@Param("reply") ReplyBean replyBean);

    //展示回复
    List<ReplyBean> getReply(@Param("reply") ReplyBean replyBean);

    //删除
    Integer deleteByPrimaryKey(Integer id);
}
