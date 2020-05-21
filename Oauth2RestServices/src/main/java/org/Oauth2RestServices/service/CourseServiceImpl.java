package org.Oauth2RestServices.service;

import org.Oauth2RestServices.models.Course;
import org.Oauth2RestServices.repository.CourseDao;
import org.Oauth2RestServices.repository.CourseDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "courseCache")
public class CourseServiceImpl implements CourseService{
    private static Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    CourseDao courseDao;

    @Override
    @Caching(
            put= { @CachePut(value= "courseCache", key= "#course.getCourseid()") },
            evict= { @CacheEvict(value= "allCoursesCache", allEntries= true) }
    )
    public Course createCourse(Course course) {
        return courseDao.createCourse(course);
    }

    @Override
    @Cacheable(value= "courseCache", key= "#p0")
    public Course getCourse(int courseId) {
        return courseDao.getCourse(courseId);
    }

    @Override
    @Cacheable(value= "allCoursesCache", unless= "#result.size() == 0")
    public List<Course> getAllCourse() {
        return courseDao.getAllCourse();
    }

    @Override
    @Caching(
            put= { @CachePut(value= "courseCache", key= "#course.getCourseid()") },
            evict= { @CacheEvict(value= "allCoursesCache", allEntries= true) }
    )
    public Course updateCourse(Course course) {
        return courseDao.updateCourse(course);
    }

    @Override
    @Caching(
            evict= {
                    @CacheEvict(value= "courseCache", key= "#courseId"),
                    @CacheEvict(value= "allCoursesCache", allEntries= true)
            }
    )
    public boolean deleteCourse(int courseId) {
        return courseDao.deleteCourse(courseId);
    }

    @Override
    @Caching(
            put= { @CachePut(value= "courseCache", key= "#courseId")}
    )
    public boolean updateApproved(int courseId){
        return courseDao.updateApproved(courseId);}
}
