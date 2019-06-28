package com.core.console.service;

import com.core.console.po.UserBean;
import com.core.console.uitl.PageInfo;

import java.util.List;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/7/20
 * @Description:
 */
public interface UserService {

    List<UserBean> getUser(UserBean userBean, PageInfo pageInfo);

    int getUserCount();

    Integer updateUser(UserBean userBean);
}
