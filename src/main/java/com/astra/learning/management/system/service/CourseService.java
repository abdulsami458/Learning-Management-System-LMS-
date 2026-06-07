package com.astra.learning.management.system.service;

import com.astra.learning.management.system.dto.CourseDto;
import com.astra.learning.management.system.model.Course;
import com.astra.learning.management.system.model.Enrollment;
import com.astra.learning.management.system.model.User;
import com.astra.learning.management.system.repository.CourseRepo;
import com.astra.learning.management.system.repository.EnrollmentRepo;
import com.astra.learning.management.system.repository.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {


    private final CourseRepo courseRepo;

    private final UserRepo userRepo;

    private final EnrollmentRepo enrollmentRepo;

    public CourseService(CourseRepo courseRepo, UserRepo userRepo, EnrollmentRepo enrollmentRepo) {
        this.courseRepo = courseRepo;
        this.userRepo = userRepo;
        this.enrollmentRepo = enrollmentRepo;
    }

    public Course createCourse(CourseDto courseDto){
        Course course = new Course();
        course.setCourseName(courseDto.getCourseName());
        course.setCourseCode(courseDto.getCourseCode());
        return courseRepo.save(course);
    }

    public List<Course> viewMyCourses(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("Email Not Found"));
        List<Enrollment> enrollments = enrollmentRepo.findByUser(user);
        return enrollments.stream()
                .map(Enrollment::getCourse)
                .toList();
    }
}
