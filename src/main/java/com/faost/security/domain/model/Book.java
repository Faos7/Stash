package com.faost.security.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "books")
public class Book implements Serializable{

    @Id
    @SequenceGenerator(name="book_sequence",sequenceName="book_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="book_sequence")
    @Column(name="id", unique=true, nullable=false)
    private Integer bookId;

    @Column(name = "Name_book")
    private String bookName;

    @Column(name = "Name_author")
    private String authorName;

    @Column(name = "Publ_year")
    private int publYear;

    @Column(name = "number")
    private int number;

    @ManyToOne
    @JoinColumn(name = "Id_library")
    private Library library;

    @Column(name = "OnlyHere")
    private Boolean onlyHere;

    @ManyToOne
    @JoinColumn(name = "Id_owner")
    private Student student;

    @Column(name = "DateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFrom;

    @Column(name = "dateTo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTo;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Boolean getOnlyHere() {
        return onlyHere;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public int getPublYear() {
        return publYear;
    }

    public Integer getBookId() {
        return bookId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Library getLibrary() {
        return library;
    }

    public String getBookName() {
        return bookName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public Student getStudent() {
        return student;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setPublYear(int publYear) {
        this.publYear = publYear;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public void setOnlyHere(Boolean onlyHere) {
        this.onlyHere = onlyHere;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book(){}

    public Book(String bookName, String authorName,
                int publYear, Library library, Boolean onlyHere,
                Student student, Date dateFrom, Date dateTo){
        this.bookName = bookName;
        this.authorName = authorName;
        this.publYear = publYear;
        this.library = library;
        this.onlyHere = onlyHere;
        this.student = student;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "Book{" + "BookId=" + bookId +
                ", bookName=" + bookName +
                ", authorName=" + authorName +
                ", publyshingYear=" + publYear +
                ", library=" + library.getLibraryId()+
                ", number=" + number +
                ", onlyHere=" + onlyHere +
                ", owner=" + student.getStudentId() +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +'}';
    }
}
