package com.core.console.dao.userdao;

import com.core.console.po.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/7/20
 * @Description:
 */
@Mapper
public interface UserDao {
    List<UserBean> getUser (@Param("userBean") UserBean userBean ,@Param("startrow") Integer startRow,@Param("size") Integer size);

    @Select("select count(1) from sys_user")
    int getUserCount ();
}
