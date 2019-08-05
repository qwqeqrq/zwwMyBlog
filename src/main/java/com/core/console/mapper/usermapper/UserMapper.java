package com.core.console.mapper.usermapper;

import com.core.console.po.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/7/20
 * @Description:
 */
@Mapper
public interface UserMapper {
    List<UserBean> getUser(@Param("userBean") UserBean userBean, @Param("startrow") Integer startRow, @Param("size") Integer size);

    @Select("select count(1) from sys_user")
    int getUserCount();

    @Update("<script> update  sys_user <set> <if test=\"userBean.userName!=null\"> user_name = #{userBean.userName}, " +
            "</if> <if test=\"userBean.userPassword!=null\"> user_password = #{userBean.userPassword}, </if> " +
            "<if test=\"userBean.userState!=0 and userBean.userState!=null\"> user_state = #{userBean.userState}, </if> " +
            "<if test=\"userBean.email !=null \"> " +
            "email=#{userBean.email}, </if> login_time = now() </set>  where user_id =#{userBean.userId}  </script>")
    Integer updateUser(@Param("userBean") UserBean userBean);
}
