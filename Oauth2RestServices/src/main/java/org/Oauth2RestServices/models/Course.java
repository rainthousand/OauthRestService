package org.Oauth2RestServices.models;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

public class Course extends RepresentationModel<Course> implements Serializable {
    private Integer courseId;

    private String courseName;

    private Integer teacherId;

    private String courseTime;

    private String classRoom;

    private Integer courseWeek;

    private String courseType;

    private Integer collegeId;

    private Integer score;

    private Boolean approved;

    public Integer getCourseid() {
        return courseId;
    }

    public void setCourseid(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCoursename() {
        return courseName;
    }

    public void setCoursename(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Integer getTeacherid() {
        return teacherId;
    }

    public void setTeacherid(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getCoursetime() {
        return courseTime;
    }

    public void setCoursetime(String courseTime) {
        this.courseTime = courseTime == null ? null : courseTime.trim();
    }

    public String getClassroom() {
        return classRoom;
    }

    public void setClassroom(String classRoom) {
        this.classRoom = classRoom == null ? null : classRoom.trim();
    }

    public Integer getCourseweek() {
        return courseWeek;
    }

    public void setCourseweek(Integer courseWeek) {
        this.courseWeek = courseWeek;
    }

    public String getCoursetype() {
        return courseType;
    }

    public void setCoursetype(String courseType) {
        this.courseType = courseType == null ? null : courseType.trim();
    }

    public Integer getCollegeid() {
        return collegeId;
    }

    public void setCollegeid(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}