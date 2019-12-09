package com.core.blog.control;

import com.core.blog.service.*;
import com.core.blog.uitls.EmailUtils;
import com.core.console.po.UserBean;
import com.core.console.service.EmailScheduleService;
import com.core.console.service.UserService;
import com.core.console.uitl.IpTools;
import com.core.console.uitl.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.*;


/**
 * 功能描述: 发送邮件
 *
 * @param:
 * @return:
 * @auther: ZHANGWEI
 * @date: 2018/12/13/00013 21:31
 */
@RestController
@RequestMapping("/Email")
public class EMailController {

    private static final Logger logger = LoggerFactory.getLogger(EMailController.class);

    @Autowired
    HuangLiServiceImpl huangLiService;
    @Autowired
    NewsApiServiceImpl newsApiService;
    @Autowired
    WeatherServiceImpl weatherService;
    @Autowired
    MovieServiceImpl movieService;
    @Autowired
    NovelServiceImpl novelService;
    @Autowired
    JokeiServiceImpl jokeiService;
    @Autowired
    UserService userService;
    //异步处理线程池
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1,
            10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));

    /**
     * @描述： 每天早上定时发送订阅邮件
     * @参数： []
     * @返回值： java.lang.String
     * @创建人： zhangww
     * @创建时间： 2019-08-13
     * @修改人和其它信息：
     */
    //@Scheduled(cron = "0 0/2 0 * * ?")
//每两分钟执行
    @Scheduled(cron = "0 05 07 ? * *")

    //每天早上6点触发
    private String sendEmail() {
        UserBean userBean = new UserBean();
        PageInfo pageInfo = new PageInfo();
        int count = 0;//记录次数
        userBean.setUserState("1");//只给状态为1的人发送邮件
        List<UserBean> userBeanList = userService.getUser(userBean, pageInfo);
        if (userBeanList.isEmpty()) {
            return "无发件人发送失败";
        }
        for (UserBean user : userBeanList) {
            String[] emailUrl = new String[1];//目的邮箱地址
            emailUrl[0] = user.getEmail();
            String emailTheme = user.getEmailTheme();//邮件主题
            String name = user.getUserName();//收件人姓名
            String cityCode = user.getCityCode();//城市编码
            String huangLi = huangLiService.getApiContent("");
            String weather = weatherService.getApiContent("", cityCode, user.getUserId());
            String movie = movieService.getApiContent("").get("email");
            String news = newsApiService.getApiContent("");
            String novel = "";
            try {
                novel = threadPoolExecutor.submit(() -> novelService.getApiContent("")).get(100, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException e) {
                logger.error("ExecutionException{}", "线程池异常");
            } catch (TimeoutException t) {
                logger.error("小说请求接口调用超时{}", "异步超时使用成功");
            }
            String joke = jokeiService.getApiContent("");
            String content = weather + "<br/>" + news + "<br/>" + huangLi + "<br/>" + movie + "<br/><br/>" + novel + "<br/>" + joke;
            if ("张维".equals(name)) {
                content = "今日已成功给" + count + "位小伙伴发送订阅邮件" + "<br/>" + weather + "<br/>" + news + "<br/>" + huangLi + "<br/>" + movie + "<br/><br/>" + novel + "<br/>" + joke;
            }
            //String[] chaoSongrenUrl = {""};//抄送邮箱地址
            //List<Map<String, String>> listMap = new ArrayList<>();附件
            boolean result = EmailUtils.sendEmail(emailTheme, emailUrl, null, content, null);
            if (!result) {
                boolean result2 = EmailUtils.sendEmail(emailTheme, emailUrl, null, content, null);
                if (!result2) {
                    boolean result3 = EmailUtils.sendEmail(emailTheme, emailUrl, null, content, null);
                    if (!result3) {
                        String[] w = {"1374559379@qq.com"};
                        EmailUtils.sendEmail("三次都失败了", w, null, name + "发送了三次都失败了", null);
                        count = count - 1;
                    }
                }
            }
            count += 1;
        }
        return "发送成功";
    }

    //恶搞他们点击退订

    @RequestMapping(value = "/unsubscribe")
    public String unsubscribe(HttpServletRequest httpServletRequest) {
        logger.info("========================点退订者ip" + IpTools.getIPCity(IpTools.getIpAddress(httpServletRequest)) + "======================");
        return "你点个牛蛙锤子！！！";
    }
}
