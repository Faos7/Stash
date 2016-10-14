package com.faost.security.domain.model.create;

import com.faost.security.domain.security.User;
import org.hibernate.validator.constraints.NotEmpty;

public class LibrarianCreateForm {

    private User user;

    @NotEmpty
    private String firstName = "";

    @NotEmpty
    private String secondName = "";

    @NotEmpty
    private String thirdName = "";

    @NotEmpty
    private String libraryName = "";

    public LibrarianCreateForm(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }
}