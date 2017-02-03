package com.faost.security.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "courses")
public class Course implements Serializable{
    @Id
    @SequenceGenerator(name="course_sequence",sequenceName="u_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="course_sequence")
    @Column(name = "Id_course")
    private Integer courseId;

    @Column(name = "Course_numb")
    private Integer number;

    @OneToMany(mappedBy = "course")
    private List<Group> groups;

    public Course(){}

    public Course(Integer courseNumb, List<Group> groups){
        this.number = courseNumb;
        this.groups = groups;
    }

    public void addGroup(Group group){
        groups.add(group);
    }

    public void deleteGroup(Group group){
        groups.remove(group);
    }

    public int getNumderOfGroups(){
        return groups.size();
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
