package com.core.blog.mapper;

import com.core.socket.DouyuDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/10/4/00004
 * @Description:
 */
@Mapper
public interface DouYuLiveMapper {
    int insert(DouyuDto douyuDto);
}
