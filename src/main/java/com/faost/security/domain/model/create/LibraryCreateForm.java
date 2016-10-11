package com.faost.security.domain.model.create;

import org.hibernate.validator.constraints.NotEmpty;

public class LibraryCreateForm {

    @NotEmpty
    private String universityName = "";

    @NotEmpty
    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
}
