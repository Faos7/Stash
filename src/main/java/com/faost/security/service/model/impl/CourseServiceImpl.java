package com.faost.security.service.model.impl;

import com.faost.security.domain.model.Course;
import com.faost.security.domain.model.Group;
import com.faost.security.domain.model.create.CourseCreateForm;
import com.faost.security.repository.model.CourseRepository;
import com.faost.security.service.model.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<Course> getCourseById(int id) {
        LOGGER.debug("Getting course={}", id);
        return Optional.ofNullable(courseRepository.findOne(id));
    }

    @Override
    public Optional<Course> getCourseByNumber(int number) {
        LOGGER.debug("Getting course by number={}", number);
        return courseRepository.findOneByNumber(number);
    }

    @Override
    public Collection<Course> getAllCourses() {
        LOGGER.debug("Getting all courses");
        return courseRepository.findAll(new Sort("number"));
    }

    @Override
    public Collection<Group> getAllCourseGroups(int number) {
        LOGGER.debug("Getting course groups, course number={}", number);
        Optional<Course> course = courseRepository.findOneByNumber(number);
        Collection<Group> groups = course.get().getGroups();
        return groups;
    }

    @Override
    public Course create(CourseCreateForm form) {
        Course course = new Course();
        course.setNumber(form.getNumb());
        return courseRepository.save(course);
    }
}
