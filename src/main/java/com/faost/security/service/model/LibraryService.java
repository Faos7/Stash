package com.faost.security.service.model;

import com.faost.security.domain.model.Book;
import com.faost.security.domain.model.Library;
import com.faost.security.domain.model.create.LibraryCreateForm;
import com.faost.security.domain.security.User;

import java.util.Collection;
import java.util.Optional;

public interface LibraryService {

    Optional<Library> getLibraryById(int id);

    Optional<Library> getLibraryByName(String name);

    Collection<Library> getAllLibraries();

    Collection<User> getAllLibraryLibrarians(String name);

    Collection<Book> getAllLibraryBooks(String name);

    Library create(LibraryCreateForm form);
}
