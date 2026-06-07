package com.astra.learning.management.system.repository;

import com.astra.learning.management.system.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course,Long> {
    Optional<Course> findById (Long id);
}
