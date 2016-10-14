package com.faost.security.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "university")
public class University implements Serializable{

    @Id
    @SequenceGenerator(name="u_sequence",sequenceName="u_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="u_sequence")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "university")
    private List<Library> libraries;

    @OneToMany(mappedBy = "university")
    private List<Faculty> faculties;

    public University() {}

    public List<Library> getLibraries() {
        return libraries;
    }

    public void addFaculty(Faculty faculty){
        faculties.add(faculty);
    }

    /*public void addGroups(int id){
        GroupRepository groupRepository = new GroupRepository(){};
        groups.add()
    }*/

    public void deleteFaculty(Faculty faculty){
        faculties.remove(faculty);
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

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
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
