package com.faost.security.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "groups")
public class Group implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_group", nullable = false)
    private Integer groupId;

    @Column(name = "Name_group")
    private String name;

    @ManyToOne
    @JoinColumn(name = "Id_univer")
    private University university;

    @ManyToOne
    @JoinColumn(name = "Id_course")
    private Course course;

    @OneToMany(mappedBy = "group")
    private List<Student> students;

    public void addStudent(Student student){
        students.add(student);
    }

    public void deleteStudent(Student student){
        students.remove(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
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

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Group(){}

    public Group(University university, Course course, String name, List<Student> students){
        this.course = course;
        this.name = name;
        this.university = university;
        this.students = students;
    }

    @Override
    public String toString() {
        return "Group{" + "groupId=" + groupId +
                ", university=" + university.getName() +
                ", course" + course.getNumber() +
                ", name " + name + '}';
    }
}