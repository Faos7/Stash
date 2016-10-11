package com.faost.security.domain.model;

import com.faost.security.domain.security.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "students")
public class Student implements Serializable{

    @Id
    @SequenceGenerator(name="st_sequence",sequenceName="student_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="st_sequence")
    @Column(name="id", unique=true, nullable=false)
    private Integer studentId;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Second_Name")
    private String secondName;

    @Column(name = "Third_Name")
    private String thirdName;

    @Column(name = "RFID_number")
    private Long numberRFID;

    @Column(name = "Phone_numb")
    private Long phoneNumb;

    @ManyToOne
    @JoinColumn(name = "Id_group")
    private Group group;

    @OneToOne(optional = false, mappedBy = "student")
    private User user;

    @OneToMany(mappedBy = "student")
    private List<Book> books;

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

    public void deleteBook(Book book){
        books.remove(book);
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public Long getPhoneNumb() {
        return phoneNumb;
    }

    public void setPhoneNumb(Long phoneNumb) {
        this.phoneNumb = phoneNumb;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Student(){}

    public Student(Group group, String firstName,
                   String secondName, String thirdName,
                   Long phoneNumb, List<Book> books){
        this.group = group;
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.phoneNumb = phoneNumb;
        this.books = books;
    }

    @Override
    public String toString() {
        return "Student{" + "StudentId=" + studentId +
                ", group=" + group.getName() +
                ", firstName=" + firstName +
                ", secondName=" + secondName +
                ", thirdName=" + thirdName +
                ", phoneNumber=" + phoneNumb + '}';
    }
}
