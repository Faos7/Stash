package com.faost.security.controller.model;

import com.faost.security.service.user.UserService;
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

    private final UserService userService;

    @Autowired
    public LibrarianController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/librarian/{id}")
    public ModelAndView getLibrarianPage(@PathVariable Long id) {
        LOGGER.debug("Getting librarian page for librarian={}", id);
        return new ModelAndView("librarian", "librarian", userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Librarian=%s not found", id))));
    }

    @RequestMapping("/librarians")
    public ModelAndView getLibrariansPage() {
        LOGGER.debug("Getting librarians page");
        return new ModelAndView("librarians", "librarians", userService.getAllUsersWithSpecifiedRole("librarian"));
    }

    /*@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/librarian/create", method = RequestMethod.GET)
    public ModelAndView getLibrarianCreatePage(User user){
        LOGGER.debug("Getting librarian create form");
        return new ModelAndView("librarian_create", "form", new LibrarianCreateForm(user));
    }*/
}