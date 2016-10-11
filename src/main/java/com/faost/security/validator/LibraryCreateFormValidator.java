package com.faost.security.validator;

import com.faost.security.domain.model.Library;
import com.faost.security.domain.model.create.LibraryCreateForm;
import com.faost.security.service.model.LibraryService;
import com.faost.security.service.model.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LibraryCreateFormValidator implements Validator{
    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryCreateFormValidator.class);
    private final LibraryService libraryService;
    private final UniversityService universityService;

    @Autowired
    public LibraryCreateFormValidator(LibraryService libraryService, UniversityService universityService) {
        this.libraryService = libraryService;
        this.universityService = universityService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(LibraryCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        LibraryCreateForm form = (LibraryCreateForm) target;
        validateUniversity(errors, form);
        validateName(errors, form);
    }

    private void validateName(Errors errors, LibraryCreateForm form) {
        if (libraryService.getLibraryByName(form.getName()).isPresent()){
            Library library = libraryService.getLibraryByName(form.getName()).get();
            if (library.getUniversity().getName().equals(form.getUniversityName())){
            errors.reject("name.exists", "Library with this name already exists");
        }
        }
    }

    private void validateUniversity(Errors errors, LibraryCreateForm form) {
        if (!universityService.getUniversityByName(form.getUniversityName()).isPresent()){
            errors.reject("no.university", "University with this name does not exist");
        }
    }
}
