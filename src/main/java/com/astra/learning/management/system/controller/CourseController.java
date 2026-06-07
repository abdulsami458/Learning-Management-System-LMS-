package com.astra.learning.management.system.controller;

import com.astra.learning.management.system.dto.CourseDto;
import com.astra.learning.management.system.model.Course;
import com.astra.learning.management.system.repository.CourseRepo;
import com.astra.learning.management.system.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController( CourseService courseService) {
        this.courseService = courseService;
    }


    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping("/createCourse")
    public ResponseEntity<String> createCourse(@RequestBody CourseDto courseDto){
        courseService.createCourse(courseDto);
        return ResponseEntity.ok("Course Created");
    }

    @GetMapping("/my-courses")
    public ResponseEntity<List<Course>> viewMyCourses(){
        List<Course> courses =courseService.viewMyCourses();
        return ResponseEntity.ok(courses);
    }
}
