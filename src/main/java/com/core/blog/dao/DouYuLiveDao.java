package com.core.blog.dao;

import com.core.socket.DouyuDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/10/4/00004
 * @Description:
 */
@Mapper
public interface DouYuLiveDao {
    int insert(DouyuDto douyuDto);
}
