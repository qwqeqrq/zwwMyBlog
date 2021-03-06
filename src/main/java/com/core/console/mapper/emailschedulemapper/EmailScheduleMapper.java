package com.core.console.mapper.emailschedulemapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * Created by zww on 2019-05-05.
 */
@Mapper
public interface EmailScheduleMapper {

    @Select("SELECT id id , title title, target_date date FROM `email_schedule` where deleted=0 and user_id =#{userId} ORDER BY sort asc LIMIT 1")
    Map<String, Object> getEmailSchedule(@Param("userId") int userId);

    @Update("update email_schedule set deleted=1 ,updateDate = now() where id = #{id} ")
    Integer updateEmailSchedule(@Param("id") int id);

}
