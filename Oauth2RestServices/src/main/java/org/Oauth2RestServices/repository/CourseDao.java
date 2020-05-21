package org.Oauth2RestServices.repository;

import org.Oauth2RestServices.models.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseDao {
    public Course createCourse(Course course);

    public Course getCourse(int courseId);

    public List<Course> getAllCourse();

    public Course updateCourse(Course course);

    public boolean deleteCourse(int courseId);

    public boolean updateApproved(int courseId);

}
