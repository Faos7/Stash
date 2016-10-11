package com.faost.security.validator;

import com.faost.security.domain.model.Faculty;
import com.faost.security.domain.model.Group;
import com.faost.security.domain.model.University;
import com.faost.security.domain.model.create.GroupCreateForm;
import com.faost.security.service.model.FacultyService;
import com.faost.security.service.model.GroupService;
import com.faost.security.service.model.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by faos7 on 09.10.16.
 */
@Component
public class GroupCreateFormValidator implements Validator{

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupCreateFormValidator.class);

    private final FacultyService facultyService;
    private final UniversityService universityService;
    private final GroupService groupService;

    @Autowired
    public GroupCreateFormValidator(FacultyService facultyService,
                                    UniversityService universityService,
                                    GroupService groupService) {
        this.facultyService = facultyService;
        this.universityService = universityService;
        this.groupService = groupService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(GroupCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        GroupCreateForm form = (GroupCreateForm) target;
        validateUniversity(errors, form);
        validateFaculty(errors, form);
        validateUniversityAndFaculty(errors, form);
        validateName(errors, form);

    }

    private void validateName(Errors errors, GroupCreateForm form){
        if (universityService.getUniversityByName(form.getUniversityName()).isPresent()){
            University university = universityService.getUniversityByName(form.getUniversityName()).get();
            if (facultyService.getFacultyByName(form.getFacultyName()).isPresent()){
                Faculty faculty = facultyService.getFacultyByName(form.getFacultyName()).get();
                if (faculty.getUniversity().equals(university)){
                    if (groupService.getGroupByName(form.getName()).isPresent()){
                        Group group = groupService.getGroupByName(form.getName()).get();
                        if (group.getFaculty().equals(faculty)){
                            errors.reject("group.exists", "Such group already exists");
                        }
                    }
                }
            }
        }
    }

    private void validateFaculty(Errors errors, GroupCreateForm form) {
        if (!facultyService.getFacultyByName(form.getFacultyName()).isPresent()){
            errors.reject("no.faculty", "Faculty with this name does not exist");
        }
    }

    public void validateUniversityAndFaculty(Errors errors, GroupCreateForm form){
        if (universityService.getUniversityByName(form.getUniversityName()).isPresent()){
            University university = universityService.getUniversityByName(form.getUniversityName()).get();
            if (facultyService.getFacultyByName(form.getFacultyName()).isPresent()){
                Faculty faculty = facultyService.getFacultyByName(form.getFacultyName()).get();
                if (!faculty.getUniversity().equals(university)){
                    errors.reject("wrong.faculty", "This Faculty do not belong to this University");
                }
            }
        }
    }

    private void validateUniversity(Errors errors, GroupCreateForm form) {
        if (!universityService.getUniversityByName(form.getUniversityName()).isPresent()){
            errors.reject("no.university", "University with this name does not exist");
        }
    }
}
