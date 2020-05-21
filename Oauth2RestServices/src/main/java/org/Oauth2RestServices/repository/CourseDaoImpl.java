package org.Oauth2RestServices.repository;

import org.Oauth2RestServices.mapper.CourseMapper;
import org.Oauth2RestServices.models.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao{
    private static Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);

    @Autowired
    CourseMapper courseMapper;

    @Override
    public Course createCourse(Course course) {
        course.setApproved(false);
        int newCourseId = courseMapper.insert(course);
        return getCourse(newCourseId);
    }

    @Override
    public Course getCourse(int courseId) {
        return courseMapper.selectByPrimaryKey(courseId);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseMapper.getAllCourse();
    }

    @Override
    public Course updateCourse(Course course) {
        courseMapper.updateByPrimaryKey(course);
        return getCourse(course.getCourseid());
    }

    @Override
    public boolean deleteCourse(int courseId) {
        return courseMapper.deleteCourse(courseId);
    }

    @Override
    public boolean updateApproved(int courseId){
        courseMapper.updateApproved(courseId);
        return getCourse(courseId).getApproved();
    }

}
