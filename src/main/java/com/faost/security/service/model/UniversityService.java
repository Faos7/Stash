package com.faost.security.service.model;

import com.faost.security.domain.model.Faculty;
import com.faost.security.domain.model.Library;
import com.faost.security.domain.model.University;
import com.faost.security.domain.model.create.UniversityCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface UniversityService {

    Optional<University> getUniversityById(int id);

    Optional<University> getUniversityByName(String name);

    Collection<University> getAllUniversities();

    University create(UniversityCreateForm form);

    Collection<Library> getAllUniversityLibraries(String name);

    Collection<Faculty> getAllUniversityFaculties(String name);
}
