package com.faost.security.service.model;

import com.faost.security.domain.model.Book;
import com.faost.security.domain.model.Librarian;
import com.faost.security.domain.model.Library;
import com.faost.security.domain.model.create.LibraryCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface LibraryService {

    Optional<Library> getLibraryById(int id);

    Optional<Library> getLibraryByName(String name);

    Collection<Library> getAllLibraries();

    Collection<Librarian> getAllLibraryLibrarians(String name);

    Collection<Book> getAllLibraryBooks(String name);

    Library create(LibraryCreateForm form);
}
