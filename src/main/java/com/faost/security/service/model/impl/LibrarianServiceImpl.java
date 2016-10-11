package com.faost.security.service.model.impl;

import com.faost.security.domain.model.Librarian;
import com.faost.security.domain.model.Library;
import com.faost.security.domain.model.create.LibrarianCreateForm;
import com.faost.security.repository.model.LibrarianRepository;
import com.faost.security.repository.model.LibraryRepository;
import com.faost.security.service.model.LibrarianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class LibrarianServiceImpl implements LibrarianService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibrarianServiceImpl.class);

    private final LibraryRepository libraryRepository;

    private final LibrarianRepository librarianRepository;

    @Autowired
    public LibrarianServiceImpl(LibraryRepository libraryRepository,
                                LibrarianRepository librarianRepository) {
        this.libraryRepository = libraryRepository;
        this.librarianRepository = librarianRepository;
    }

    @Override
    public Optional<Librarian> getLibrarianById(int id) {
        LOGGER.debug("Getting librarian={}", id);
        return Optional.ofNullable(librarianRepository.findOne(id));
    }

    @Override
    public Collection<Librarian> getLibrariansByFirstName(String firstName) {
        LOGGER.debug("Getting all librarians with firstName{}", firstName);
        Collection<Librarian> result = null;
        for (Optional<Librarian> librarian : librarianRepository.findManyByFirstName(firstName)){
            if (librarian.isPresent()) {
                result.add(librarian.get());
            }
        }
        return result;
    }

    @Override
    public Collection<Librarian> getLibrariansBySecondName(String secondName) {
        LOGGER.debug("Getting all librarians with secondName{}", secondName);
        Collection<Librarian> result = null;
        for (Optional<Librarian> librarian : librarianRepository.findManyBySecondName(secondName)){
            if (librarian.isPresent()) {
                result.add(librarian.get());
            }
        }
        return result;
    }

    @Override
    public Collection<Librarian> getLibrariansByThirdName(String thirdName) {
        LOGGER.debug("Getting all librarians with thirdName{}", thirdName);
        Collection<Librarian> result = null;
        for (Optional<Librarian> librarian : librarianRepository.findManyByThirdName(thirdName)){
            if (librarian.isPresent()) {
                result.add(librarian.get());
            }
        }
        return result;
    }

    @Override
    public Collection<Librarian> getAllLibrarians() {
        LOGGER.debug("Getting all librarians");
        return librarianRepository.findAll(new Sort("second_name"));
    }

    @Override
    public Librarian create(LibrarianCreateForm form) {
        Optional<Library> library = libraryRepository.findOneByName(form.getLibraryName());
        Librarian librarian = new Librarian();
        librarian.setFirstName(form.getFirstName());
        librarian.setSecondName(form.getSecondName());
        librarian.setThirdName(form.getThirdName());
        librarian.setLibrary(library.get());
        library.get().addLibrarian(librarian);
        libraryRepository.save(library.get());
        return librarianRepository.save(librarian);
    }
}
