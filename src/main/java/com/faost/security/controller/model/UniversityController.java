package com.faost.security.controller.model;

import com.faost.security.domain.model.create.UniversityCreateForm;
import com.faost.security.service.model.UniversityService;
import com.faost.security.validator.UniversityCreateFormValidator;
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
public class UniversityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UniversityController.class);

    private final UniversityService universityService;
    private final UniversityCreateFormValidator universityCreateFormValidator;

    @Autowired
    public UniversityController(UniversityService universityService, UniversityCreateFormValidator universityCreateFormValidator)
    {
        this.universityCreateFormValidator = universityCreateFormValidator;
        this.universityService = universityService;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(universityCreateFormValidator);
    }

    @RequestMapping("/university/{id}")
    public ModelAndView getUniversityPage(@PathVariable Integer id) {
        LOGGER.debug("Getting university page for university={}", id);
        return new ModelAndView("university", "university", universityService.getUniversityById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("University=%s not found", id))));
    }

    @RequestMapping("/universities")
    public ModelAndView getUniversitiesPage() {
        LOGGER.debug("Getting universities page");
        return new ModelAndView("universities", "universities", universityService.getAllUniversities());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/university/create", method = RequestMethod.GET)
    public ModelAndView getUniversityCreatePage() {
        LOGGER.debug("Getting university create form");
        return new ModelAndView("university_create", "form", new UniversityCreateForm());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/university/create", method = RequestMethod.POST)
    public String handleUniversityCreateForm(@Valid @ModelAttribute("form") UniversityCreateForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing university create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "university_create";
        }
        try {
            universityService.create(form);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the university, assuming duplicate name", e);
            bindingResult.reject("name.exists", "University with such name already exists");
            return "university_create";
        }
        // ok, redirect
        return "redirect:/universities";
    }
}
