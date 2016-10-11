package com.faost.security.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "faculty")
public class Faculty implements Serializable{

    @Id
    @SequenceGenerator(name="faq_sequence",sequenceName="faculty_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="faq_sequence")
    @Column(name="id", unique=true, nullable=false)
    private Integer Id;

    @Column(name = "Name_faculty")
    private String name;

    @ManyToOne
    @JoinColumn(name = "Id_univer")
    private University university;

    @OneToMany(mappedBy = "faculty")
    private List<Group> groups;

    public Faculty() {}

    public void addGroup(Group group){
        groups.add(group);
    }

    public void deleteGroup(Group group){
        groups.remove(group);
    }

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", university=" + university +
                '}';
    }
}
