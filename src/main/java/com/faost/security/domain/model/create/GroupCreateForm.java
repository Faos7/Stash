package com.faost.security.domain.model.create;

import org.hibernate.validator.constraints.NotEmpty;

public class GroupCreateForm {

    @NotEmpty
    private String name = "";

    @NotEmpty
    private String universityName = "";

    @NotEmpty
    private int courseNumber;

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

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }
}
