package com.core.blog.mapper;

import com.core.blog.po.StudentScores;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentScoresMapper {

    @Insert("insert into student_scores (`name`, classes, chinese, math," +
            " english, wuli, huaxue, shengwu,lizonghe, total, grade_rank, class_rank, " +
            "test_title, createDate) values(#{s.name},#{s.classes},#{s.chinese},#{s.math}" +
            ",#{s.english},#{s.wuli},#{s.huaxue},#{s.shengwu},#{s.lizonghe},#{s.total},#{s.gradeRank}," +
            "#{s.classRank},#{s.testTitle},now())")
    int insert(@Param("s") StudentScores studentScores);

    @Select("SELECT id, name, classes, chinese, math, english, wuli, huaxue, shengwu," +
            " lizonghe, total, grade_rank gradeRank , class_rank classRank, test_title testTitle FROM " +
            "student_scores where deleted = 0 and name = #{name}")
    List<StudentScores> findcustomer(@Param("name") String name);

    @Delete("update student_scores set deleted=1 where test_title = #{title}")
    int delete(String title);
}
