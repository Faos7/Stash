package com.faost.security.service.model;

import com.faost.security.domain.model.Book;
import com.faost.security.domain.model.create.BookCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface BookService {

    Optional<Book> getBookById(int id);

    Optional<Book> getBookByNumber(int number);

    Collection<Book> getAllBooks();

    Collection<Book> getBooksByBookName(String bookName);

    Collection<Book> getBooksByAuthorName(String authorName);

    Collection<Book> getBooksByPublishingYear(int publYear);

    Book create(BookCreateForm form);
}
