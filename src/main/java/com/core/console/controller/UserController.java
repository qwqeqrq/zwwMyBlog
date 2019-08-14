package com.core.console.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.core.blog.po.Result;
import com.core.blog.uitls.StringUtils;
import com.core.console.po.UserBean;
import com.core.console.service.UserService;
import com.core.console.uitl.IpTools;
import com.core.console.uitl.PageInfo;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/7/20
 * @Description:
 */
@Controller
@RequestMapping(value = "/sys")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    //用户管理类
    @Autowired
    UserService userService;

    @RequestMapping(value = "user")
    public String getUser(HttpServletRequest request, PageInfo page, Model model) {
        /**
         *
         * 功能描述: 根据用户id获取系统用户信息 用户管理列表
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
                userBean.setUserId(Integer.valueOf(request.getParameter("userId")));
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
            logger.error(JSONUtils.toJSONString(e));
            result.setCode(-1);
            result.setData(null);
            result.setMsg("服务异常！请求失败");
            model.addAttribute("result", result);
            return "user";
        }
    }

    /**
     * @描述： 登录验证接口
     * @参数：
     * @返回值：
     * @创建人： zhangww
     * @创建时间： 2019-06-28
     * @修改人和其它信息：
     */
    @RequestMapping(value = "login")
    public String doLogin(UserBean user, Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>访问者ip:" + IpTools.getIpAddress(httpServletRequest));
        //httpServletResponse.setContentType("text/html;charset=utf-8");
        Result result = new Result();
        try {
            if (checkUserCodeisEmpty(user)){
                logger.info("=================空用户名密码在刷新登录页================");
                result.setCode(0);
                model.addAttribute("result", result);
                return "login";//跳转登录页
            }
            String passWord = user.getUserPassword();
            user.setUserPassword("");
            List<UserBean> userBeanList = userService.getUser(user, null);
            if (userBeanList != null && !userBeanList.isEmpty()) {
                if (userBeanList.get(0).getUserPassword().equals(passWord)) {
                    result.setCode(0);
                    model.addAttribute("result", result);
                    httpServletRequest.getSession().setAttribute("userId", userBeanList.get(0).getUserId());
                    UserBean userBean = new UserBean();
                    userBean.setUserId(userBeanList.get(0).getUserId());
                    userService.updateUser(userBean);
                    return "index";
                } else {
                    result.setCode(-1);
                    result.setMsg("用户名或者密码错误！");
                    model.addAttribute("result", result);
                    return "login";
                }
            } else {
                result.setCode(-1);
                result.setMsg("用户不存在！");
                model.addAttribute("result", result);
                return "login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(JSONUtils.toJSONString(e));
        }
        return "系统维护中！！！敬请谅解";
    }

    /**
     * @描述： 检查前端输入的用户名是否为空
     * @参数： [user]
     * @返回值： boolean
     * @创建人： zhangww
     * @创建时间： 2019-08-14
     * @修改人和其它信息：
     */
    private boolean checkUserCodeisEmpty(UserBean user) {
        return StringUtils.isEmpty(user.getUserCode());
    }
}
