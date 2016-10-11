package com.faost.security.service.model.impl;

import com.faost.security.domain.model.Book;
import com.faost.security.domain.model.Librarian;
import com.faost.security.domain.model.Library;
import com.faost.security.domain.model.University;
import com.faost.security.domain.model.create.LibraryCreateForm;
import com.faost.security.repository.model.LibraryRepository;
import com.faost.security.repository.model.UniversityRepository;
import com.faost.security.service.model.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryServiceImpl.class);

    private final LibraryRepository libraryRepository;

    private final UniversityRepository universityRepository;

    @Autowired
    public LibraryServiceImpl(LibraryRepository libraryRepository,
                              UniversityRepository universityRepository) {
        this.libraryRepository = libraryRepository;
        this.universityRepository = universityRepository;
    }
    @Override
    public Optional<Library> getLibraryById(int id) {
        LOGGER.debug("Getting library={}", id);
        return Optional.ofNullable(libraryRepository.findOne(id));
    }

    @Override
    public Optional<Library> getLibraryByName(String name) {
        LOGGER.debug("Getting library by name={}", name);
        return libraryRepository.findOneByName(name);
    }

    @Override
    public Collection<Library> getAllLibraries() {
        LOGGER.debug("Getting all libraries");
        return libraryRepository.findAll(new Sort("name"));
    }

    @Override
    public Collection<Librarian> getAllLibraryLibrarians(String name) {
        LOGGER.debug("Getting library librarians, library name={}", name);
        Optional<Library> library = libraryRepository.findOneByName(name);
        Collection<Librarian> librarians = library.get().getLibrarien();
        return librarians;
    }

    @Override
    public Collection<Book> getAllLibraryBooks(String name) {
        LOGGER.debug("Getting library books, library name={}", name);
        Optional<Library> library = libraryRepository.findOneByName(name);
        Collection<Book> books = library.get().getBooks();
        return books;
    }

    @Override
    public Library create(LibraryCreateForm form) {
        Optional<University> university = universityRepository.findOneByName(form.getUniversityName());
        Library library = new Library();
        library.setName(form.getName());
        library.setUniversity(university.get());
        university.get().addLibrary(library);
        universityRepository.save(university.get());
        return libraryRepository.save(library);
    }
}
