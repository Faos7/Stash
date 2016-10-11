package com.faost.security.validator;

import com.faost.security.domain.model.Book;
import com.faost.security.domain.model.Library;
import com.faost.security.domain.model.University;
import com.faost.security.domain.model.create.BookCreateForm;
import com.faost.security.service.model.BookService;
import com.faost.security.service.model.LibraryService;
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
public class BookCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookCreateFormValidator.class);

    private final BookService bookService;
    private final UniversityService universityService;
    private final LibraryService libraryService;

    @Autowired
    public BookCreateFormValidator(BookService bookService,
                                   UniversityService universityService,
                                   LibraryService libraryService) {
        this.bookService = bookService;
        this.universityService = universityService;
        this.libraryService = libraryService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(BookCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        BookCreateForm form = (BookCreateForm) target;
        validateUniversity(errors, form);
        validateLibrary(errors, form);
        validateUniversityAndLibrary(errors, form);
        validateNumber(errors, form);
    }

    private void validateNumber(Errors errors, BookCreateForm form){
        if (universityService.getUniversityByName(form.getUniversityName()).isPresent()){
            University university = universityService.getUniversityByName(form.getUniversityName()).get();
            if (libraryService.getLibraryByName(form.getLibraryName()).isPresent()){
                Library library = libraryService.getLibraryByName(form.getLibraryName()).get();
                if (library.getUniversity().equals(university)){
                    if (bookService.getBookByNumber(form.getNumber()).isPresent()){
                        Book book = bookService.getBookByNumber(form.getNumber()).get();
                        if (book.getLibrary().equals(library)){
                            errors.reject("book.exists", "Such book already exists");
                        }
                    }
                }
            }
        }
    }

    public void validateUniversityAndLibrary(Errors errors, BookCreateForm form){
        if (universityService.getUniversityByName(form.getUniversityName()).isPresent()){
            University university = universityService.getUniversityByName(form.getUniversityName()).get();
            if (libraryService.getLibraryByName(form.getLibraryName()).isPresent()){
                Library library = libraryService.getLibraryByName(form.getLibraryName()).get();
                if (!library.getUniversity().equals(university)){
                    errors.reject("wrong.library", "This Library do not belong to this University");
                }
            }
        }
    }

    private void validateLibrary(Errors errors, BookCreateForm form) {
        if (!libraryService.getLibraryByName(form.getLibraryName()).isPresent()){
            errors.reject("no.library", "Library with this name does not exist");
        }
    }

    private void validateUniversity(Errors errors, BookCreateForm form) {
        if (!universityService.getUniversityByName(form.getUniversityName()).isPresent()){
            errors.reject("no.university", "University with this name does not exist");
        }
    }
}
