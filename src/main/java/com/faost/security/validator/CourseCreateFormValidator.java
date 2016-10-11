package com.faost.security.validator;

import com.faost.security.domain.model.create.CourseCreateForm;
import com.faost.security.service.model.CourseService;
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
public class CourseCreateFormValidator implements Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseCreateFormValidator.class);
    private final CourseService courseService;

    @Autowired
    public CourseCreateFormValidator(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CourseCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        CourseCreateForm form = (CourseCreateForm) target;
        validateNumber(errors, form);
    }

    private void validateNumber(Errors errors, CourseCreateForm form) {
        if (courseService.getCourseByNumber(form.getNumb()).isPresent()){
            errors.reject("number.exists", "Course with this number already exists");
        }
    }
}
