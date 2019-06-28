package com.core.console.serviceimpl;

import com.core.console.mapper.usermapper.UserMapper;
import com.core.console.po.UserBean;
import com.core.console.service.UserService;
import com.core.console.uitl.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/7/20
 * @Description:
 */
@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserMapper userDao;

    /**
     * @描述： 分页查询用户列表
     * @参数：
     * @返回值：
     * @创建人： zhangww
     * @创建时间： 2019-06-28
     * @修改人和其它信息：
     */
    @Override
    public List<UserBean> getUser(UserBean userBean, PageInfo page) {
        if (page != null) {
            Integer startRow = page.getStartRow();
            Integer size = page.getSize();
            return userDao.getUser(userBean, startRow, size);
        } else {
            return userDao.getUser(userBean, null, null);
        }

    }

    @Override
    public int getUserCount() {
        return userDao.getUserCount();
    }

    //更新用户信息
    @Override
    public Integer updateUser(UserBean userBean) {
        Integer result = userDao.updateUser(userBean);
        return result;
    }
}
