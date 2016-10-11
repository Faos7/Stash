package com.faost.security.service.model.impl;

import com.faost.security.domain.model.Faculty;
import com.faost.security.domain.model.Group;
import com.faost.security.domain.model.University;
import com.faost.security.domain.model.create.FacultyCreateForm;
import com.faost.security.repository.model.FacultyRepository;
import com.faost.security.repository.model.UniversityRepository;
import com.faost.security.service.model.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by faos7 on 27.09.16.
 */
@Service
public class FacultyServiceImpl implements FacultyService{

    private static final Logger LOGGER = LoggerFactory.getLogger(FacultyServiceImpl.class);

    private final FacultyRepository facultyRepository;

    private final UniversityRepository universityRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository,
                              UniversityRepository universityRepository) {
        this.facultyRepository = facultyRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    public Optional<Faculty> getFacultyById(int id) {
        LOGGER.debug("Getting faculty={}", id);
        return Optional.ofNullable(facultyRepository.findOne(id));
    }

    @Override
    public Optional<Faculty> getFacultyByName(String name) {
        LOGGER.debug("Getting faculty by name={}", name);
        return facultyRepository.findOneByName(name);
    }

    @Override
    public Collection<Faculty> getAllFaculties() {
        LOGGER.debug("Getting all faculties");
        return facultyRepository.findAll(new Sort("name"));
    }

    @Override
    public Faculty create(FacultyCreateForm form) {
        Optional<University> university = universityRepository.findOneByName(form.getUniversityName());
        Faculty faculty = new Faculty();
        faculty.setName(form.getName());
        faculty.setUniversity(university.get());
        university.get().addFaculty(faculty);
        universityRepository.save(university.get());
        return facultyRepository.save(faculty);
    }

    @Override
    public Collection<Group> getAllFacultyGroups(String name) {
        LOGGER.debug("Getting faculty groups, faculty name={}", name);
        Optional<Faculty> faculty = facultyRepository.findOneByName(name);
        Collection<Group> groups = faculty.get().getGroups();
        return groups;
    }
}
