package com.faost.security.service.model;

import com.faost.security.domain.model.Faculty;
import com.faost.security.domain.model.Group;
import com.faost.security.domain.model.create.FacultyCreateForm;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by faos7 on 27.09.16.
 */
public interface FacultyService {

    Optional<Faculty> getFacultyById(int id);

    Optional<Faculty> getFacultyByName(String name);

    Collection<Faculty> getAllFaculties();

    Faculty create(FacultyCreateForm form);

    Collection<Group> getAllFacultyGroups(String name);
}
