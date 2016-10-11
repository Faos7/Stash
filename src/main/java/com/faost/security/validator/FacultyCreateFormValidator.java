package com.faost.security.validator;

import com.faost.security.domain.model.Faculty;
import com.faost.security.domain.model.create.FacultyCreateForm;
import com.faost.security.service.model.FacultyService;
import com.faost.security.service.model.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by faos7 on 08.10.16.
 */
@Component
public class FacultyCreateFormValidator  implements Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FacultyCreateFormValidator.class);
    private final FacultyService facultyService;
    private final UniversityService universityService;

    @Autowired
    public FacultyCreateFormValidator(FacultyService facultyService, UniversityService universityService) {
        this.facultyService = facultyService;
        this.universityService = universityService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(FacultyCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        FacultyCreateForm form = (FacultyCreateForm) target;
        validateUniversity(errors, form);
        validateName(errors, form);
    }

    private void validateName(Errors errors, FacultyCreateForm form) {
        if (facultyService.getFacultyByName(form.getName()).isPresent()){
            Faculty faculty = facultyService.getFacultyByName(form.getName()).get();
            if (faculty.getUniversity().getName().equals(form.getUniversityName())){
            errors.reject("name.exists", "Faculty with this name already exists");
        }
        }
    }

    private void validateUniversity(Errors errors, FacultyCreateForm form) {
        if (!universityService.getUniversityByName(form.getUniversityName()).isPresent()){
            errors.reject("no.university", "University with this name does not exist");
        }
    }
}
