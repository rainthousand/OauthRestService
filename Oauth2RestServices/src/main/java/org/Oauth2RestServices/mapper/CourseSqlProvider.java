package org.Oauth2RestServices.mapper;

import org.Oauth2RestServices.models.Course;
import org.apache.ibatis.jdbc.SQL;

public class CourseSqlProvider {

    public String insertSelective(Course record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("course");
        
        if (record.getCourseid() != null) {
            sql.VALUES("courseId", "#{courseid,jdbcType=INTEGER}");
        }
        
        if (record.getCoursename() != null) {
            sql.VALUES("courseName", "#{coursename,jdbcType=VARCHAR}");
        }
        
        if (record.getTeacherid() != null) {
            sql.VALUES("teacherId", "#{teacherid,jdbcType=INTEGER}");
        }
        
        if (record.getCoursetime() != null) {
            sql.VALUES("courseTime", "#{coursetime,jdbcType=VARCHAR}");
        }
        
        if (record.getClassroom() != null) {
            sql.VALUES("classRoom", "#{classroom,jdbcType=VARCHAR}");
        }
        
        if (record.getCourseweek() != null) {
            sql.VALUES("courseWeek", "#{courseweek,jdbcType=INTEGER}");
        }
        
        if (record.getCoursetype() != null) {
            sql.VALUES("courseType", "#{coursetype,jdbcType=VARCHAR}");
        }
        
        if (record.getCollegeid() != null) {
            sql.VALUES("collegeId", "#{collegeid,jdbcType=INTEGER}");
        }
        
        if (record.getScore() != null) {
            sql.VALUES("score", "#{score,jdbcType=INTEGER}");
        }
        
        if (record.getApproved() != null) {
            sql.VALUES("approved", "#{approved,jdbcType=BIT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Course record) {
        SQL sql = new SQL();
        sql.UPDATE("course");
        
        if (record.getCoursename() != null) {
            sql.SET("courseName = #{coursename,jdbcType=VARCHAR}");
        }
        
        if (record.getTeacherid() != null) {
            sql.SET("teacherId = #{teacherid,jdbcType=INTEGER}");
        }
        
        if (record.getCoursetime() != null) {
            sql.SET("courseTime = #{coursetime,jdbcType=VARCHAR}");
        }
        
        if (record.getClassroom() != null) {
            sql.SET("classRoom = #{classroom,jdbcType=VARCHAR}");
        }
        
        if (record.getCourseweek() != null) {
            sql.SET("courseWeek = #{courseweek,jdbcType=INTEGER}");
        }
        
        if (record.getCoursetype() != null) {
            sql.SET("courseType = #{coursetype,jdbcType=VARCHAR}");
        }
        
        if (record.getCollegeid() != null) {
            sql.SET("collegeId = #{collegeid,jdbcType=INTEGER}");
        }
        
        if (record.getScore() != null) {
            sql.SET("score = #{score,jdbcType=INTEGER}");
        }
        
        if (record.getApproved() != null) {
            sql.SET("approved = #{approved,jdbcType=BIT}");
        }
        
        sql.WHERE("courseId = #{courseid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}