package com.core.blog.service;

import com.core.blog.po.StudentScores;

import java.util.List;

public interface StudentScoresServer {

    int deleteByTitle(String title);

    List<StudentScores> findByName(String name);

    int insert(List<StudentScores> studentScores);
}
