package com.core.blog.service;

import com.core.blog.mapper.StudentScoresMapper;
import com.core.blog.po.StudentScores;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentScoresServerImpl implements StudentScoresServer {

    private static final Logger logger = LoggerFactory.getLogger(StudentScoresServerImpl.class);
    @Autowired
    private StudentScoresMapper studentScoresMapper;

    @Override
    public int deleteByTitle(String title) {
        return studentScoresMapper.delete(title);
    }

    @Override
    public List<StudentScores> findByName(String name) {
        return studentScoresMapper.findcustomer(name);
    }

    @Override
    public int insert(List<StudentScores> studentScores) {
        int i = 0;
        String title = "";
        if (studentScores != null && studentScores.size() > 0) {
            title = studentScores.get(0).getTestTitle();
            for (StudentScores s : studentScores) {
                int r = studentScoresMapper.insert(s);
                if (r < 1) {
                    logger.error("插入学生数据异常 请查看");
                    break;
                }
                i++;
            }
        }
        logger.info("成功插入学生{}成绩数据{}条", title, i);
        return i;
    }
}
