package com.faost.security.controller.model;

import com.faost.security.domain.model.create.FacultyCreateForm;
import com.faost.security.service.model.FacultyService;
import com.faost.security.validator.FacultyCreateFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
public class FacultyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FacultyController.class);

    private final FacultyService facultyService;
    private final FacultyCreateFormValidator facultyCreateFormValidator;

    @Autowired
    public FacultyController(FacultyService facultyService, FacultyCreateFormValidator facultyCreateFormValidator) {
        this.facultyService = facultyService;
        this.facultyCreateFormValidator = facultyCreateFormValidator;
    }


    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(facultyCreateFormValidator);
    }

    @RequestMapping("/faculty/{id}")
    public ModelAndView getFacultyPage(@PathVariable Integer id) {
        LOGGER.debug("Getting faculty page for faculty={}", id);
        return new ModelAndView("faculty", "faculty", facultyService.getFacultyById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Faculty=%s not found", id))));
    }

    @RequestMapping("/faculties")
    public ModelAndView getFacultiesPage() {
        LOGGER.debug("Getting faculties page");
        return new ModelAndView("faculties", "faculties", facultyService.getAllFaculties());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/faculty/create", method = RequestMethod.GET)
    public ModelAndView getFacultyCreatePage() {
        LOGGER.debug("Getting faculty create form");
        return new ModelAndView("faculty_create", "form", new FacultyCreateForm());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/faculty/create", method = RequestMethod.POST)
    public String handleLFacultyCreateForm(@Valid @ModelAttribute("form") FacultyCreateForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing faculty create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "faculty_create";
        }
        try {
            facultyService.create(form);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the faculty, assuming duplicate name", e);
            bindingResult.reject("name.exists", "Faculty with such name already exists");
            return "faculty_create";
        }
        // ok, redirect
        return "redirect:/faculties";
    }

}
