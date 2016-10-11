package com.faost.security.domain.model.create;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class BookCreateForm {

    @NotEmpty
    private String universityName;

    @NotEmpty
    private String bookName = "";

    @NotEmpty
    private String authorName = "";

    @NotNull
    private int publishingYear;

    @NotEmpty
    private String libraryName = "";

    @NotNull
    private int number = -1;

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }
}
