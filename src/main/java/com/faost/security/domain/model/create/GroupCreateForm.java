package com.faost.security.domain.model.create;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class GroupCreateForm {

    @NotEmpty
    private String name = "";

    @NotEmpty
    private String facultyName = "";

    @NotNull
    private int courseNumber = -1;

    @NotEmpty
    private String universityName;

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }
}
