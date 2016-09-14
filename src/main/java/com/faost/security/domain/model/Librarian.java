package com.faost.security.domain.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "librarians")
public class Librarian implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_librarian", nullable = false)
    private Integer librarianId;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Second_Name")
    private String secondName;

    @Column(name = "Third_Name")
    private String thirdName;

    @ManyToOne
    @JoinColumn(name = "Id_library")
    private Library library;

    public Librarian(){}

    public Librarian(Library library, String firstName, String secondName, String thirdName){
        this.firstName = firstName;
        this.library = library;
        this.thirdName = thirdName;
        this.secondName = secondName;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Integer getLibrarianid() {
        return librarianId;
    }

    public void setLibrarianid(Integer librarianId) {
        this.librarianId = librarianId;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    @Override
    public String toString() {
        return "Librarian{" + "librarianId=" + librarianId +
                ", libraryId=" + library.getLibraryId() +
                ", FirstName=" + firstName +
                ", SecondName=" + secondName +
                ", ThirdName=" + thirdName + '}';
    }
}