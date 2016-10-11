package com.faost.security.controller.model;

import com.faost.security.domain.model.create.LibraryCreateForm;
import com.faost.security.service.model.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;

/**
 * Created by faos7 on 26.09.16.
 */
@Controller
public class LibraryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @RequestMapping("/library/{id}")
    public ModelAndView getLibraryPage(@PathVariable Integer id) {
        LOGGER.debug("Getting library page for library={}", id);
        return new ModelAndView("library", "library", libraryService.getLibraryById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Library=%s not found", id))));
    }

    @RequestMapping("/libraries")
    public ModelAndView getLibrariesPage() {
        LOGGER.debug("Getting libraries page");
        return new ModelAndView("libraries", "libraries", libraryService.getAllLibraries());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/library/create", method = RequestMethod.GET)
    public ModelAndView getLibraryCreatePage() {
        LOGGER.debug("Getting library create form");
        return new ModelAndView("lib_create", "form", new LibraryCreateForm());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/library/create", method = RequestMethod.POST)
    public String handleLibraryCreateForm(@Valid @ModelAttribute("form") LibraryCreateForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing library create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "lib_create";
        }
        try {
            libraryService.create(form);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the library, assuming duplicate name", e);
            bindingResult.reject("name.exists", "Library with such name already exists");
            return "lib_create";
        }
        // ok, redirect
        return "redirect:/libraries";
    }
}
