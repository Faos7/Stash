package com.faost.security.domain.model;


import com.faost.security.domain.security.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "groups")
public class Group implements Serializable{

    @Id
    @SequenceGenerator(name="gr_sequence",sequenceName="gr_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gr_sequence")
    @Column(name="id", unique=true, nullable=false)
    private Integer groupId;

    @Column(name = "Name_group")
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_faculty")
    private Faculty faculty;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_course")
    private Course course;

    @OneToMany(mappedBy = "group")
    private List<User> users;

    public void addStudent(User student){
        users.add(student);
    }

    public void deleteStudent(User student){
        users.remove(student);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Group(){}

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                ", faculty=" + faculty +
                ", course=" + course +
                '}';
    }
}
