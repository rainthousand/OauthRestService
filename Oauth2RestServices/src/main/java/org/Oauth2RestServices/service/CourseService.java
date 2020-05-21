package org.Oauth2RestServices.service;

import org.Oauth2RestServices.models.Course;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    public Course createCourse(Course course);

    public Course getCourse(int courseId);

    public List<Course> getAllCourse();

    public Course updateCourse(Course course);

    public boolean deleteCourse(int courseId);

    public boolean updateApproved(int courseId);
}
