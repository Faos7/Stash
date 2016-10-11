package com.faost.security.service.model;

import com.faost.security.domain.model.Course;
import com.faost.security.domain.model.Group;
import com.faost.security.domain.model.create.CourseCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface CourseService {

    Optional<Course> getCourseById(int id);

    Optional<Course> getCourseByNumber(int number);

    Collection<Course> getAllCourses();

    Course create(CourseCreateForm form);

    Collection<Group> getAllCourseGroups(int number);
}
