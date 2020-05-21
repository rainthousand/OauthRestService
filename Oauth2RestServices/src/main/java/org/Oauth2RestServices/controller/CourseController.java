package org.Oauth2RestServices.controller;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.Oauth2RestServices.mapper.CourseMapper;
import org.Oauth2RestServices.models.Course;
import org.Oauth2RestServices.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/elearning")
@Tag(name = "course", description = "the Course API")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CourseController {
    private static Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    CourseService courseService;

    @Autowired
    CourseMapper courseMapper;



    @Operation(summary = "Add a new Course", description = "", tags = { "course" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course created",
                    content = @Content(schema = @Schema(implementation = Course.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Contact already exists") })
    @RequestMapping(value="/new/course", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('INVENTORY_ADD')")
    public Course createCourse(@Parameter(description="Course to add. Cannot null or empty.", required=true, schema=@Schema(implementation = Course.class))
                                   @RequestBody Course course){

        return courseService.createCourse(course);
    }


    @Operation(summary = "Find course by ID", description = "Returns a single course with its Hateoas", tags = { "course" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Course.class))),
            @ApiResponse(responseCode = "404", description = "Course not found") })

    @RequestMapping(value="/courses/{courseId}",method = RequestMethod.GET, produces= MediaTypes.HAL_JSON_VALUE)
    @PreAuthorize("hasAuthority('INVENTORY_VIEW')")
    public Course getCourse( @Parameter(description="Id of the Course to be obtained. Cannot be empty.", required=true)
                                @PathVariable int courseId){
        logger.info("calling course service");
        Course course = courseService.getCourse(courseId);

        Link getCourseLink = WebMvcLinkBuilder.linkTo(CourseController.class).slash("courses").slash(course.getCourseid()).withSelfRel();
        Link delCourselink = WebMvcLinkBuilder.linkTo(CourseController.class).slash("course ").slash(course.getCourseid()).withRel("delete course").withMedia("DELETE").withTitle("delete course");
        Link postCourselink = WebMvcLinkBuilder.linkTo(CourseController.class).slash("update/course").withRel("update course").withMedia("POST").withTitle("update course");
        course.add(getCourseLink);
        course.add(delCourselink);
        course.add(postCourselink);


        return course;
    }

    @Operation(summary = "Find all courses", description = "", tags = { "course" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Course.class)))) })

    @GetMapping(value="/courses", produces=MediaTypes.HAL_JSON_VALUE)
    @PreAuthorize("hasAuthority('INVENTORY_VIEW')")
    public List<Course> getAllCourse(){
        List<Course> coursesWithLinks= new ArrayList<>();
        List<Course> courses = courseService.getAllCourse();
        if(!CollectionUtils.isEmpty(courses) ){
            for(Course course : courses){

                Link getCourselink = WebMvcLinkBuilder.linkTo(CourseController.class).slash("courses").slash(course.getCourseid()).withSelfRel();
                Link delCourselink = WebMvcLinkBuilder.linkTo(CourseController.class).slash("course")
                        .slash(course.getCourseid()).withRel("delete course").withMedia("DELETE").withTitle("delete course");

                course.add(getCourselink);
                course.add(delCourselink);
                coursesWithLinks.add(course);
            }
        }
        return coursesWithLinks;
    }

    @Operation(summary = "Update a Course", description = "", tags = { "course" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course updated",
                    content = @Content(schema = @Schema(implementation = Course.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Course not exists") })

    @RequestMapping(value="/update/course",method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('INVENTORY_ADD')")
    public Course updateCourse( @Parameter(description="Course to update. Cannot null or empty.", required=true, schema=@Schema(implementation = Course.class))
                                @RequestBody Course course){
        return courseService.updateCourse(course);
    }


    @Operation(summary = "Update Approved", description = "Return True if the course approved, False if error encountered", tags = { "course" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course updated",
                    content = @Content(schema = @Schema(implementation = Course.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Course not exists") })

    @RequestMapping(value="/update/course/approved/{courseId}",method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('INVENTORY_ADD')")
    public boolean updateApproved( @Parameter(description="Course to update. Cannot null or empty.", required=true)
                                @PathVariable int courseId){

        return courseService.updateApproved(courseId);
    }


    @Operation(summary = "输入Course ID, 删除相应的Course记录", description = "Return True if the course deleted, False if error encountered.", tags = { "course" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Course.class))),
            @ApiResponse(responseCode = "404", description = "Course not found") })

    @RequestMapping(value="/course/{courseId}", method = RequestMethod.DELETE )
    @PreAuthorize("hasAuthority('INVENTORY_ADD')")
    public boolean deleteCourse( @Parameter(description="Id of the Course to be deleted. Cannot be empty.", required=true)
                                   @PathVariable int courseId){

        return courseService.deleteCourse(courseId);
    }

}
