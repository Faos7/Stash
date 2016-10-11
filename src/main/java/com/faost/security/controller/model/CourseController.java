package com.faost.security.controller.model;

import com.faost.security.domain.model.create.CourseCreateForm;
import com.faost.security.service.model.CourseService;
import com.faost.security.validator.CourseCreateFormValidator;
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

/**
 * Created by faos7 on 26.09.16.
 */
@Controller
public class CourseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    private final CourseService courseService;
    private final CourseCreateFormValidator courseCreateFormValidator;

    @Autowired
    public CourseController(CourseService courseService, CourseCreateFormValidator courseCreateFormValidator) {
        this.courseService = courseService;
        this.courseCreateFormValidator = courseCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(courseCreateFormValidator);
    }

    @RequestMapping("/course/{id}")
    public ModelAndView getCoursePage(@PathVariable Integer id) {
        LOGGER.debug("Getting course page for course={}", id);
        return new ModelAndView("course", "course", courseService.getCourseById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Course=%s not found", id))));
    }

    @RequestMapping("/courses")
    public ModelAndView getCoursesPage() {
        LOGGER.debug("Getting courses page");
        return new ModelAndView("courses", "courses", courseService.getAllCourses());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/course/create", method = RequestMethod.GET)
    public ModelAndView getCourseCreatePage() {
        LOGGER.debug("Getting course create form");
        return new ModelAndView("course_create", "form", new CourseCreateForm());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/course/create", method = RequestMethod.POST)
    public String handleCourseCreateForm(@Valid @ModelAttribute("form") CourseCreateForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing course create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "course_create";
        }
        try {
            courseService.create(form);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the course, assuming duplicate number", e);
            bindingResult.reject("number.exists", "Course with such number already exists");
            return "course_create";
        }
        // ok, redirect
        return "redirect:/courses";
    }
}
