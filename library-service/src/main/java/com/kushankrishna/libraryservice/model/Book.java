package com.kushankrishna.libraryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.time.LocalDate;
@Builder
@Entity
@Table(name = "book_table")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Bookid;
    @Column(unique = true)
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Invalid ISBM Number")
    String ISBN;
    String bookName;
    String bookAuthor;
    String publisher;
    Double bookPrice;
    LocalDate publishedDate;
    Boolean isIssued;
    String customerId;
    LocalDate issuedDate;
    LocalDate returnDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "library")
    Library library;

    public Long getBookid() {
        return Bookid;
    }
    @JsonIgnore
    public void setBookid(Long bookid) {
        Bookid = bookid;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }
    public Boolean getIssued() {
        return isIssued;
    }

    public void setIssued(Boolean issued) {
        isIssued = issued;
    }

    public String getCustomerId() {
        return customerId;
    }
    @JsonIgnore
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Library getLibrary() {
        return library;
    }
    @JsonIgnore
    public void setLibrary(Library library) {
        this.library = library;
    }

    public Book() {
    }

    public Book(Long bookid, String ISBN, String bookName, String bookAuthor, String publisher, Double bookPrice, LocalDate publishedDate, Boolean isIssued, String customerId, LocalDate issuedDate, LocalDate returnDate, Library library) {
        Bookid = bookid;
        this.ISBN = ISBN;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.publisher = publisher;
        this.bookPrice = bookPrice;
        this.publishedDate = publishedDate;
        this.isIssued = isIssued;
        this.customerId = customerId;
        this.issuedDate = issuedDate;
        this.returnDate = returnDate;
        this.library = library;
    }

    @Override
    public String toString() {
        return "Book{" +
                "Bookid=" + Bookid +
                ", ISBN='" + ISBN + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", publisher='" + publisher + '\'' +
                ", bookPrice=" + bookPrice +
                ", publishedDate=" + publishedDate +
                ", isIssued=" + isIssued +
                ", customerId='" + customerId + '\'' +
                ", issuedDate=" + issuedDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
