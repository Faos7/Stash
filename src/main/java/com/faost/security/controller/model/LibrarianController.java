package com.faost.security.controller.model;

import com.faost.security.service.model.LibrarianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

/**
 * Created by re4 on 12.10.16.
 */

@Controller
public class LibrarianController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibrarianController.class);

    private final LibrarianService librarianService;

    @Autowired
    public LibrarianController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @RequestMapping("/librarian/{id}")
    public ModelAndView getLibrarianPage(@PathVariable Integer id) {
        LOGGER.debug("Getting librarian page for librarian={}", id);
        return new ModelAndView("librarian", "librarian", librarianService.getLibrarianById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Librarian=%s not found", id))));
    }

    @RequestMapping("/librarians")
    public ModelAndView getLibrariansPage() {
        LOGGER.debug("Getting librarians page");
        return new ModelAndView("librarians", "librarians", librarianService.getAllLibrarians());
    }
}
