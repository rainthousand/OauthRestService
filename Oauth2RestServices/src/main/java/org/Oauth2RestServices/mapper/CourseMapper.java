package org.Oauth2RestServices.mapper;

import org.Oauth2RestServices.models.Course;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface CourseMapper {
    @Delete({
        "delete from course",
        "where courseId = #{courseid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer courseid);

    @Insert({
        "insert into course (courseId, courseName, ",
        "teacherId, courseTime, ",
        "classRoom, courseWeek, ",
        "courseType, collegeId, ",
        "score, approved)",
        "values (#{courseid,jdbcType=INTEGER}, #{coursename,jdbcType=VARCHAR}, ",
        "#{teacherid,jdbcType=INTEGER}, #{coursetime,jdbcType=VARCHAR}, ",
        "#{classroom,jdbcType=VARCHAR}, #{courseweek,jdbcType=INTEGER}, ",
        "#{coursetype,jdbcType=VARCHAR}, #{collegeid,jdbcType=INTEGER}, ",
        "#{score,jdbcType=INTEGER}, #{approved,jdbcType=BIT})"
    })
    int insert(Course record);

    @InsertProvider(type=CourseSqlProvider.class, method="insertSelective")
    int insertSelective(Course record);

    @Select({
        "select",
        "courseId, courseName, teacherId, courseTime, classRoom, courseWeek, courseType, ",
        "collegeId, score, approved",
        "from course",
        "where courseId = #{courseid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="courseId", property="courseid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="courseName", property="coursename", jdbcType=JdbcType.VARCHAR),
        @Result(column="teacherId", property="teacherid", jdbcType=JdbcType.INTEGER),
        @Result(column="courseTime", property="coursetime", jdbcType=JdbcType.VARCHAR),
        @Result(column="classRoom", property="classroom", jdbcType=JdbcType.VARCHAR),
        @Result(column="courseWeek", property="courseweek", jdbcType=JdbcType.INTEGER),
        @Result(column="courseType", property="coursetype", jdbcType=JdbcType.VARCHAR),
        @Result(column="collegeId", property="collegeid", jdbcType=JdbcType.INTEGER),
        @Result(column="score", property="score", jdbcType=JdbcType.INTEGER),
        @Result(column="approved", property="approved", jdbcType=JdbcType.BIT)
    })
    Course selectByPrimaryKey(Integer courseid);

    @UpdateProvider(type=CourseSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Course record);

    @Update({
        "update course",
        "set courseName = #{coursename,jdbcType=VARCHAR},",
          "teacherId = #{teacherid,jdbcType=INTEGER},",
          "courseTime = #{coursetime,jdbcType=VARCHAR},",
          "classRoom = #{classroom,jdbcType=VARCHAR},",
          "courseWeek = #{courseweek,jdbcType=INTEGER},",
          "courseType = #{coursetype,jdbcType=VARCHAR},",
          "collegeId = #{collegeid,jdbcType=INTEGER},",
          "score = #{score,jdbcType=INTEGER},",
          "approved = #{approved,jdbcType=BIT}",
        "where courseId = #{courseid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Course record);

    @Select("select * from course")
    @Results({
            @Result(property = "courseId", column = "courseId"),
            @Result(property = "courseName", column = "courseName")})
    public List<Course> getAllCourse();

    @Delete("delete from course where courseId=#{courseid} ")
    public boolean deleteCourse(@Param("courseid") int courseid);

    @Update({
            "update course",
            "set approved = true",
            "where courseId = #{courseid,jdbcType=INTEGER}"
    })
    public int updateApproved(@Param("courseid") int courseid);
}