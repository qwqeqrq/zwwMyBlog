package com.core.blog.control;

import com.core.blog.po.ArticleBean;
import com.core.blog.po.Result;
import com.core.blog.service.ArticleService;
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
@RequestMapping(value = "Article")
public class ArticleControl {
    @Autowired
    ArticleService articleService;

    /**
     *
     * 功能描述: 查询博客文章
     *
     * @param:
     * @return:
     * @auther: ZHANGWEI
     * @date: 2018/8/6/00006 15:37
     */
    @RequestMapping(value ="article" )
    @ResponseBody
    public Result<List<ArticleBean>> getArticle(ArticleBean articleBean){
        Result<List<ArticleBean>> result = new Result();
       try {
           List<ArticleBean> articleBeanList = articleService.getArticle(articleBean);
           if (articleBeanList==null||articleBeanList.isEmpty()){
               return null;
           }
           result.setCode(0);
           result.setData(articleBeanList);
           return result;
       }catch (Exception e){
           e.printStackTrace();
            result.setCode(-1);
           return result;
       }

    }


    /**
     *
     * 功能描述: 新增博客文章
     *
     * @param:
     * @return:
     * @auther: ZHANGWEI
     * @date: 2018年8月6日16:50:57
     */
    @RequestMapping(value ="addArticle" )
    @ResponseBody
    public Result addArticle(ArticleBean articleBean){
        Result result = new Result();
        try {
            Integer articleBeanList = articleService.writeArticle(articleBean);
            if (articleBeanList<=0){
                return null;
            }
            result.setCode(0);
            result.setData(articleBeanList);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(-1);
            return result;
        }

    }
}
