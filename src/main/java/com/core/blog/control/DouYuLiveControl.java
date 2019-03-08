package com.core.blog.control;

import com.core.blog.service.DouYuLiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/10/4/00004
 * @Description:
 */
@RestController
@RequestMapping(value="DouYu")
public class DouYuLiveControl {
    @Autowired
    DouYuLiveService douYuLiveService;
    @RequestMapping("Save")
    public String saveDouYuLive(){
        Integer result = douYuLiveService.saveDouYuLive();
        if (result<=0){
            return "程序异常保存爬虫斗鱼信息失败！";
        }
        return "成功爬取"+result+"条信息";
    }
}
