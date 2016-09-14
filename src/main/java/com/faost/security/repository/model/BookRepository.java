package com.faost.security.repository.model;

import com.faost.security.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findOneByNumber(int Number);

    Collection<Optional<Book>> findManyByBookName(String bookName);

    Collection<Optional<Book>> findManyByAuthorName(String authorName);

    Collection<Optional<Book>> findManyByPublYear(int publYear);
}
