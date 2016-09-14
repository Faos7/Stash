package com.faost.security.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "university")
public class University implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "university")
    private List<Library> libraries;

    @OneToMany(mappedBy = "university")
    private List<Group> groups;

    public University() {}

    public University(String univerName){
        this.name = univerName;
    }

    public University(String univerName, List<Library> libraries, List<Group> groups) {
        this.name = univerName;
        this.libraries = libraries;
        this.groups = groups;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public void addGroup(Group group){
        groups.add(group);
    }

    /*public void addGroups(int id){
        GroupRepository groupRepository = new GroupRepository(){};
        groups.add()
    }*/

    public void deleteGroup(Group group){
        groups.remove(group);
    }

    public void addLibrary(Library library){
        libraries.add(library);
    }

    public  void deleteLibrary(Library library){
        libraries.remove(library);
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }

    public int getUniverId() {
        return id;
    }

    public void setUniverId(Integer univerId) {
        this.id = univerId;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' + '}';
    }
}