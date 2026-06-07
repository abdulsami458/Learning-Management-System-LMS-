package com.astra.learning.management.system.repository;

import com.astra.learning.management.system.model.Enrollment;
import com.astra.learning.management.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepo extends JpaRepository<Enrollment,Long> {
    List<Enrollment> findByUser(User user);
}
