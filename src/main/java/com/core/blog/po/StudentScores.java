package com.core.blog.po;

import java.util.Date;

/**
 * 高中   学生分数表
 */
public class StudentScores {

    private int id;// id,
    private String name;// 姓名,
    private String classes;// 班级
    private String chinese;// 语文成绩,
    private String math;// 数学成绩,
    private String english;// 英语成绩,
    private String wuli;// 物理成绩,
    private String huaxue;// 化学成绩,
    private String shengwu;// 生物成绩,
    private String lizonghe;// 理综和总成绩,
    private String total;// 总分,
    private String gradeRank;// 年级名次,
    private String classRank;// 班级名次,
    private String testTitle;// 考试标题,
    private int deleted;// 0 未删除  1已删除,
    private Date createDate;// 创建时间,
    private Date updateDate;// 修改时间,

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getWuli() {
        return wuli;
    }

    public void setWuli(String wuli) {
        this.wuli = wuli;
    }

    public String getHuaxue() {
        return huaxue;
    }

    public void setHuaxue(String huaxue) {
        this.huaxue = huaxue;
    }

    public String getShengwu() {
        return shengwu;
    }

    public void setShengwu(String shengwu) {
        this.shengwu = shengwu;
    }

    public String getLizonghe() {
        return lizonghe;
    }

    public void setLizonghe(String lizonghe) {
        this.lizonghe = lizonghe;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getGradeRank() {
        return gradeRank;
    }

    public void setGradeRank(String gradeRank) {
        this.gradeRank = gradeRank;
    }

    public String getClassRank() {
        return classRank;
    }

    public void setClassRank(String classRank) {
        this.classRank = classRank;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
