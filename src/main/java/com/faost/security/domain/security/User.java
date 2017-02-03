package com.faost.security.domain.security;

import com.faost.security.domain.model.Book;
import com.faost.security.domain.model.Group;
import com.faost.security.domain.model.Library;
import com.faost.security.domain.model.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "users")
public class User implements Serializable{

    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="user_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name="id", unique=true, nullable=false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "third_name")
    private String thirdName;
    /*
        @Column(name = "RFID_number")
        private Long numberRFID;
    */
    @Column(name = "phone_number")
    private Long phoneNumb;

    @Column(name ="student_card_id")
    private Long studentCardId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_library")
    private Library library;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_group")
    private Group group;

    @OneToMany(mappedBy = "user")
    private List<Book> books;

    @Column(name = "username")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Transaction> stTransactions;

    @JsonIgnore
    @OneToMany(mappedBy = "librarian")
    private List<Transaction> lbTransactions;

    public List<Transaction> getStTransactions() {
        return stTransactions;
    }

    public void setStTransactions(List<Transaction> stTransactions) {
        this.stTransactions = stTransactions;
    }

    public List<Transaction> getLbTransactions() {
        return lbTransactions;
    }

    public void setLbTransactions(List<Transaction> lbTransactions) {
        this.lbTransactions = lbTransactions;
    }

    public Long getStudentCardId() {
        return studentCardId;
    }

    public void setStudentCardId(Long studentCardId) {
        this.studentCardId = studentCardId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
    /*
        public Long getNumberRFID() {
            return numberRFID;
        }
        public void setNumberRFID(Long numberRFID) {
            this.numberRFID = numberRFID;
        }
    */
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

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void deleteBook(Book book){
        books.remove(book);
    }
}
