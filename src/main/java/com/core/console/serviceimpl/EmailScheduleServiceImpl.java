package com.core.console.serviceimpl;

import com.core.console.mapper.emailschedulemapper.EmailScheduleMapper;
import com.core.console.service.EmailScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Map;

/**
 * Created by zww on 2019-05-05.
 */
@Service
public class EmailScheduleServiceImpl implements EmailScheduleService {

    @Autowired
    private EmailScheduleMapper emailScheduleDao;

    @Override
    public Map<String, Object> getEmailSchedule(int userId) {
        try {
            return emailScheduleDao.getEmailSchedule(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer updateEmailSchedule(int id) {
        try {
            return emailScheduleDao.updateEmailSchedule(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
