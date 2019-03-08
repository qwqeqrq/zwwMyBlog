package com.core.console.serviceimpl;

import com.core.console.dao.userdao.UserDao;
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
public class UserServiceImp implements UserService{
    @Autowired
    UserDao userDao;
    @Override
    public List<UserBean> getUser(UserBean userBean , PageInfo page) {
        if(page!=null){
            Integer startRow = page.getStartRow();
            Integer size= page.getSize();
            return userDao.getUser(userBean, startRow, size);
        }else {
            return userDao.getUser(userBean,null,null);
        }

    }

    @Override
    public int getUserCount() {
        return userDao.getUserCount();
    }
}
