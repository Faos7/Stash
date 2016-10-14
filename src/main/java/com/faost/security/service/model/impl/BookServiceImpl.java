package com.faost.security.service.model.impl;

import com.faost.security.domain.model.Book;
import com.faost.security.domain.model.Library;
import com.faost.security.domain.model.create.BookCreateForm;
import com.faost.security.repository.model.BookRepository;
import com.faost.security.repository.model.LibraryRepository;
import com.faost.security.service.model.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;

    private final LibraryRepository libraryRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, LibraryRepository libraryRepository) {
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
    }

    @Override
    public Optional<Book> getBookById(int id) {
        LOGGER.debug("Getting book={}", id);
        return Optional.ofNullable(bookRepository.findOne(id));
    }

    @Override
    public Optional<Book> getBookByNumber(int number) {
        LOGGER.debug("Getting book by number={}", number);
        return bookRepository.findOneByNumber(number);
    }

    @Override
    public Collection<Book> getAllBooks() {
        LOGGER.debug("Getting all books");
        return bookRepository.findAll(new Sort("number"));
    }

    @Override
    public Collection<Book> getBooksByBookName(String bookName) {
        LOGGER.debug("Getting all books with bookName{}", bookName);
        Collection<Book> result = null;
        for (Optional<Book> book : bookRepository.findManyByBookName(bookName)){
            if (book.isPresent()) {
                result.add(book.get());
            }
        }
        return result;
    }

    @Override
    public Collection<Book> getBooksByAuthorName(String authorName) {
        LOGGER.debug("Getting all books with autorName{}", authorName);
        Collection<Book> result = null;
        for (Optional<Book> book : bookRepository.findManyByAuthorName(authorName)){
            if (book.isPresent()) {
                result.add(book.get());
            }
        }
        return result;
    }

    @Override
    public Collection<Book> getBooksByPublishingYear(int publYear) {
        LOGGER.debug("Getting all books with publishingYear{}", publYear);
        Collection<Book> result = null;
        for (Optional<Book> book : bookRepository.findManyByPublYear(publYear)){
            if (book.isPresent()) {
                result.add(book.get());
            }
        }
        return result;
    }

    @Override
    public Book create(BookCreateForm form) {
        Optional<Library> library = libraryRepository.findOneByName(form.getLibraryName());
        Book book = new Book();
        book.setAuthorName(form.getAuthorName());
        book.setBookName(form.getBookName());
        book.setNumber(""+form.getNumber());
        book.setPublYear(form.getPublishingYear());
        book.setLibrary(library.get());
        library.get().addBook(book);
        libraryRepository.save(library.get());
        return bookRepository.save(book);
    }
}
