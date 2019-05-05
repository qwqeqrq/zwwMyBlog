package com.core.console.service;

import java.util.Map;

/**
 * Created by zww on 2019-05-05.邮件日程服务
 */
public interface EmailScheduleService {

    //根据邮件日程用户id查询日期
    Map<String, Object> getEmailSchedule(int userId);

   //删除邮件日程
    Integer updateEmailSchedule(int id);
}
