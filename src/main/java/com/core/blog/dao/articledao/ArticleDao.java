package com.core.blog.dao.articledao;

import com.core.blog.po.ArticleBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/7/23
 * @Description:
 */
@Mapper
public interface ArticleDao {

    //获取文章
    List<ArticleBean> getArticle (@Param("article") ArticleBean articleBean );

    //文章博客入库
    Integer writeArticle(@Param("articleBean") ArticleBean articleBean);
}
