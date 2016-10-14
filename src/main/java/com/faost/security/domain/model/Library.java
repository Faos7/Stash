package com.faost.security.domain.model;


import com.faost.security.domain.security.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "libraries")
public class Library implements Serializable{

    @Id
    @SequenceGenerator(name="lib_sequence",sequenceName="lib_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="lib_sequence")
    @Column(name="id", unique=true, nullable=false)
    private Integer libraryId;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_univer")
    private University university;

    @OneToMany(mappedBy = "library")
    private List<User> users;

    @OneToMany(mappedBy = "library")
    private List<Book> books;

    public void deleteLibrarian(User librarian){users.remove(librarian);}

    public void addLibrarian(User librarian){
        users.add(librarian);
    }

    public void deleteBook(Book book){
        books.remove(book);
    }

    public void addBook(Book book){
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Integer libraryId) {
        this.libraryId = libraryId;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Library(){}



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Library{" +
                "libraryId=" + libraryId +
                ", name='" + name + '\'' +
                ", university=" + university.getName() +
                '}';
    }
}
