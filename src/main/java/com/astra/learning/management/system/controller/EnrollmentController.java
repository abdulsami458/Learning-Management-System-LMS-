package com.astra.learning.management.system.controller;

import com.astra.learning.management.system.Role.Role;
import com.astra.learning.management.system.dto.CourseDto;
import com.astra.learning.management.system.dto.EnrollmentRequest;
import com.astra.learning.management.system.repository.EnrollmentRepo;
import com.astra.learning.management.system.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnrollmentController {
   private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }


    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/enroll")
    public ResponseEntity<String> enroll(@RequestBody EnrollmentRequest enrollmentRequest){
        enrollmentService.enroll(enrollmentRequest);
        return ResponseEntity.ok("Enrollment Successful");
    }
}
