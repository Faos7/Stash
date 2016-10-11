package com.faost.security.domain.model.create;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by faos7 on 27.09.16.
 */
public class FacultyCreateForm {

    @NotEmpty
    private String name = "";

    @NotEmpty
    private String universityName = "";

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
