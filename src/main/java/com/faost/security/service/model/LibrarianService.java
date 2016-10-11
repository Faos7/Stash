package com.faost.security.service.model;

import com.faost.security.domain.model.Librarian;
import com.faost.security.domain.model.create.LibrarianCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface LibrarianService {

    Optional<Librarian> getLibrarianById(int id);

    Collection<Librarian> getLibrariansByFirstName(String firstName);

    Collection<Librarian> getLibrariansBySecondName(String secondName);

    Collection<Librarian> getLibrariansByThirdName(String thirdName);

    Collection<Librarian> getAllLibrarians();

    Librarian create(LibrarianCreateForm form);
}
