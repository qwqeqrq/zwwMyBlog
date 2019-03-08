package com.core.console.controller;

import com.core.blog.po.Result;
import com.core.console.po.UserBean;
import com.core.console.service.UserService;
import com.core.console.uitl.PageInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/7/20
 * @Description:
 */
@Controller
@RequestMapping(value = "/sys")
public class UserController {
    //用户管理类
    @Autowired
    UserService userService;

    @RequestMapping(value = "user")
    public String getUser(HttpServletRequest request, PageInfo page, Model model) {
        /**
         *
         * 功能描述: 根据用户id获取系统用户信息
         *
         * @param: [request]
         * @return: java.util.List<com.core.console.po.UserBean>
         * @auther: ZHANGWEI
         * @date: 2018/7/20 16:13
         */
        page.setSize(15);//分页一页显示数量
        if (page.getNum() == 0) {//分页判断以便于初始化第一页第一次默认进去空值为0，便设置为一
            page.setNum(1);
        }
        page.returnStartRow();
        page.setRowCount(userService.getUserCount());
        page.returnPageCount(page.getRowCount(), page.getSize());
        UserBean userBean = new UserBean();
        Result result = new Result();
        try {
            String userId = request.getParameter("userId");
            if (userId != null && !"".equals(userId)) {
                userBean.setUserId(Long.parseLong(request.getParameter("userId")));
            }
            List<UserBean> list = userService.getUser(userBean, page);
            result.setCode(0);
            result.setData(list);
            result.setMsg("请求成功");
            model.addAttribute("result", result);
            model.addAttribute("page", page);
            return "user";
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(-1);
            result.setData(null);
            result.setMsg("服务异常！请求失败");
            model.addAttribute("result", result);
            return "user";
        }
    }

    @RequestMapping(value = "login/{user}")
    public String doLogin(UserBean user, Model model) {
        String passWord = user.getUserPassword();
        user.setUserPassword("");
        Result result = new Result();
        List<UserBean> userBeanList = userService.getUser(user, null);
        if (userBeanList != null && !userBeanList.isEmpty()) {
            if (userBeanList.get(0).getUserPassword().equals(passWord)) {
                result.setCode(0);
                model.addAttribute("result", result);
                return "index";
            } else {
                result.setCode(-1);
                result.setMsg("用户名或者密码错误！");
                model.addAttribute("result", result);
                return "login";
            }
        }else {
            result.setCode(-1);
            result.setMsg("用户不存在！");
            model.addAttribute("result", result);
            return "login";
        }

    }
}
