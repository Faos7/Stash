package com.faost.security.domain.model.create;

import javax.validation.constraints.NotNull;

public class CourseCreateForm {

    @NotNull
    private Integer numb = null;

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }
}
