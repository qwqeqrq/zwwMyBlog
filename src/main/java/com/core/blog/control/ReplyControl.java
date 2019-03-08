package com.core.blog.control;

import com.core.blog.po.ReplyBean;
import com.core.blog.po.Result;
import com.core.blog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/8/6/00006
 * @Description:
 */
@Controller
@RequestMapping(value = "Reply")
public class ReplyControl {
   @Autowired
   ReplyService replyService;


    /**
     *
     * 功能描述: 查询回复内容
     *
     * @param: [replyBean]
     * @return: com.core.blog.po.Result
     * @auther: ZHANGWEI
     * @date: 2018/8/6/00006 16:52
     */
   @RequestMapping(value="reply")
   @ResponseBody
    public Result getReply(ReplyBean replyBean){
       Result result = new Result();
       List<ReplyBean> replyBeans = replyService.getReply(replyBean);
       if (replyBeans==null||replyBeans.isEmpty()){
           return null;
       }
       result.setData(replyBeans);
       return  result;
    }
}
