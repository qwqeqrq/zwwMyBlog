package com.core.blog.service;

import com.core.blog.po.ArticleBean;

import java.util.List;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/8/6/00006
 * @Description:文章
 */
public interface ArticleService {

    //获取文章
    List<ArticleBean> getArticle (ArticleBean articleBean );

    //文章新增
    Integer writeArticle(ArticleBean articleBean);
}
