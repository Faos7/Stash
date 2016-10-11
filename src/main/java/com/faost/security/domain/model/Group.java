package com.faost.security.domain.model;

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

    @ManyToOne
    @JoinColumn(name = "Id_faculty")
    private Faculty faculty;

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