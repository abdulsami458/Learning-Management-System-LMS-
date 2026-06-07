package com.astra.learning.management.system.service;

import com.astra.learning.management.system.Role.Role;
import com.astra.learning.management.system.dto.EnrollmentRequest;
import com.astra.learning.management.system.model.Course;
import com.astra.learning.management.system.model.Enrollment;
import com.astra.learning.management.system.model.User;
import com.astra.learning.management.system.repository.CourseRepo;
import com.astra.learning.management.system.repository.EnrollmentRepo;
import com.astra.learning.management.system.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private final EnrollmentRepo enrollmentRepo;

    private final CourseRepo courseRepo;

    private final UserRepo userRepo;

    public EnrollmentService(EnrollmentRepo enrollmentRepo, CourseRepo courseRepo, UserRepo userRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.courseRepo = courseRepo;
        this.userRepo = userRepo;
    }

    public Enrollment enroll(EnrollmentRequest enrollmentRequest){
        Course course = courseRepo.findById(enrollmentRequest.getCourseId()).orElseThrow(
                ()-> new RuntimeException("No Course Found")
        );
        User user = userRepo.findById(enrollmentRequest.getUserId()).orElseThrow(
                ()-> new RuntimeException("No User Found")
        );

        if(user.getRole()!=Role.ROLE_STUDENT){
            throw new RuntimeException("Only Students Can Enroll");
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setUser(user);
        return enrollmentRepo.save(enrollment);
    }
}
