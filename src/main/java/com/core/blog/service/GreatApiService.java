package com.core.blog.service;


/**
 * Created by zww on 2018-12-14.伟大的API数据获取接口
 */
public interface GreatApiService {
    //获得api数据
    String getApiContent(String ApiUrl) throws Exception;
}
