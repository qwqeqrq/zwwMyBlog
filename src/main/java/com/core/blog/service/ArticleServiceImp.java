package com.core.blog.service;

import com.core.blog.mapper.articlemapper.ArticleMapper;
import com.core.blog.po.ArticleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/8/6/00006
 * @Description:
 */
@Service
public class ArticleServiceImp implements ArticleService {
    @Autowired
    ArticleMapper articleDao;

    @Override
    public List<ArticleBean> getArticle(ArticleBean articleBean) {
        return articleDao.getArticle(articleBean);
    }

    @Override
    public Integer writeArticle(ArticleBean articleBean) {
        return articleDao.writeArticle(articleBean);
    }
}
