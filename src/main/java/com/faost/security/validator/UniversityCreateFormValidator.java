package com.faost.security.validator;

import com.faost.security.domain.model.create.UniversityCreateForm;
import com.faost.security.service.model.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by faos7 on 26.09.16.
 */
@Component
public class UniversityCreateFormValidator implements Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(UniversityCreateFormValidator.class);
    private final UniversityService universityService;

    @Autowired
    public UniversityCreateFormValidator(UniversityService universityService) {
        this.universityService = universityService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UniversityCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        UniversityCreateForm form = (UniversityCreateForm) target;
        validateName(errors, form);
    }

    private void validateName(Errors errors, UniversityCreateForm form) {
        if (universityService.getUniversityByName(form.getName()).isPresent()){
            errors.reject("name.exists", "University with this name already exists");
        }
    }
}