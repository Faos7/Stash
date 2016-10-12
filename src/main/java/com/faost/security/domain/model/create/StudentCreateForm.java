package com.faost.security.domain.model.create;

import com.faost.security.domain.security.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class StudentCreateForm {

    private User user;

    @NotEmpty
    private String firstName = "";

    @NotEmpty
    private String secondName = "";

    @NotEmpty
    private String thirdName = "";

    @NotEmpty
    private String groupName = "";

    @NotNull
    private long phoneNumber;

    @NotNull
    private Long numberRFID;

    public StudentCreateForm(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getNumberRFID() {
        return numberRFID;
    }

    public void setNumberRFID(Long numberRFID) {
        this.numberRFID = numberRFID;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
