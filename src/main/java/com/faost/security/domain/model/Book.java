package com.faost.security.domain.model;

import com.faost.security.domain.security.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    private String number;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_library")
    private Library library;

    @Column(name = "OnlyHere")
    private Boolean onlyHere;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_owner")
    private User user;

    @Column(name = "DateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFrom;

    @Column(name = "dateTo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTo;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<Transaction> transactions;

    @JoinColumn(name = "free")
    private boolean isFree;

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = library.getLibraryId() + number;
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

    public User getUser() {
        return user;
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

    public void setUser(User user) {
        this.user = user;
    }

    public Book(){}

    @Override
    public String toString() {
        return "Book{" + "BookId=" + bookId +
                ", bookName=" + bookName +
                ", authorName=" + authorName +
                ", publyshingYear=" + publYear +
                ", library=" + library.getLibraryId()+
                ", number=" + number +
                ", onlyHere=" + onlyHere +
                ", owner=" + user.getId() +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +'}';
    }


}
