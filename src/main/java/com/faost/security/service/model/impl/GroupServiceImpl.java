package com.faost.security.service.model.impl;

import com.faost.security.domain.model.Course;
import com.faost.security.domain.model.Faculty;
import com.faost.security.domain.model.Group;
import com.faost.security.domain.model.Student;
import com.faost.security.domain.model.create.GroupCreateForm;
import com.faost.security.repository.model.CourseRepository;
import com.faost.security.repository.model.FacultyRepository;
import com.faost.security.repository.model.GroupRepository;
import com.faost.security.service.model.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {


    private static final Logger LOGGER = LoggerFactory.getLogger(GroupServiceImpl.class);

    private final GroupRepository groupRepository;

    private final FacultyRepository facultyRepository;

    private final CourseRepository courseRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository,
                            FacultyRepository facultyRepository,
                            CourseRepository courseRepository) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Optional<Group> getGroupById(int id) {
        LOGGER.debug("Getting group={}", id);
        return Optional.ofNullable(groupRepository.findOne(id));
    }

    @Override
    public Optional<Group> getGroupByName(String name) {
        LOGGER.debug("Getting group by name={}", name);
        return groupRepository.findOneByName(name);
    }

    @Override
    public Collection<Group> getAllGroups() {
        LOGGER.debug("Getting all groups");
        return groupRepository.findAll(new Sort("name"));
    }

    @Override
    public Collection<Student> getAllGroupStudents(String name) {
        LOGGER.debug("Getting group students, group name={}", name);
        Optional<Group> group = groupRepository.findOneByName(name);
        Collection<Student> students = group.get().getStudents();
        return students;
    }

    @Override
    public Group create(GroupCreateForm form) {
        Optional<Faculty> faculty = facultyRepository.findOneByName(form.getFacultyName());
        Optional<Course> course = courseRepository.findOneByNumber(form.getCourseNumber());
        Group group = new Group();
        group.setName(form.getName());
        group.setCourse(course.get());
        group.setFaculty(faculty.get());
        faculty.get().addGroup(group);
        course.get().addGroup(group);
        courseRepository.save(course.get());
        facultyRepository.save(faculty.get());
        return groupRepository.save(group);
    }
}