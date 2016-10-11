package com.faost.security.controller.model;

import com.faost.security.domain.model.create.BookCreateForm;
import com.faost.security.service.model.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;

/**
 * Created by faos7 on 09.10.16.
 */
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/book/{id}")
    public ModelAndView getBookPage(@PathVariable Integer id) {
        LOGGER.debug("Getting book page for book={}", id);
        return new ModelAndView("book", "book", bookService.getBookById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Book=%s not found", id))));
    }

    @RequestMapping("/books")
    public ModelAndView getBooksPage() {
        LOGGER.debug("Getting books page");
        return new ModelAndView("books", "books", bookService.getAllBooks());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/book/create", method = RequestMethod.GET)
    public ModelAndView getBookCreatePage() {
        LOGGER.debug("Getting book create form");
        return new ModelAndView("book_create", "form", new BookCreateForm());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/book/create", method = RequestMethod.POST)
    public String handleBookCreateForm(@Valid @ModelAttribute("form") BookCreateForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing book create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "book_create";
        }
        try {
            bookService.create(form);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the book, assuming duplicate number", e);
            bindingResult.reject("book.exists", "Such book already exists");
            return "book_create";
        }
        // ok, redirect
        return "redirect:/books";
    }
}
