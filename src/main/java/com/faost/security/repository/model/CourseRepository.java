package com.faost.security.repository.model;

import com.faost.security.domain.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findOneByNumber(int number);
}
