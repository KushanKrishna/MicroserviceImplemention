package com.kushankrishna.libraryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;
@Builder
@Entity

@Table(name = "library_table")
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String libraryName;
    Long availableBooksCount;
    Long issuedBooksCount;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "library")
    List<Book> availableBookList;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "library")
    List<Book> issuedBookList;

    public Long getId() {
        return id;
    }
    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public Long getAvailableBooksCount() {
        return availableBooksCount;
    }

    public void setAvailableBooksCount(Long availableBooksCount) {
        this.availableBooksCount = availableBooksCount;
    }

    public Long getIssuedBooksCount() {
        return issuedBooksCount;
    }
    @JsonIgnore
    public void setIssuedBooksCount(Long issuedBooksCount) {
        this.issuedBooksCount = issuedBooksCount;
    }

    public List<Book> getAvailableBookList() {
        return availableBookList;
    }

    public void setAvailableBookList(List<Book> availableBookList) {
        this.availableBookList = availableBookList;
    }

    public List<Book> getIssuedBookList() {
        return issuedBookList;
    }
    @JsonIgnore
    public void setIssuedBookList(List<Book> issuedBookList) {
        this.issuedBookList = issuedBookList;
    }

    public Library() {
    }

    public Library(Long id, String libraryName, Long availableBooksCount, Long issuedBooksCount, List<Book> availableBookList, List<Book> issuedBookList) {
        this.id = id;
        this.libraryName = libraryName;
        this.availableBooksCount = availableBooksCount;
        this.issuedBooksCount = issuedBooksCount;
        this.availableBookList = availableBookList;
        this.issuedBookList = issuedBookList;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", libraryName='" + libraryName + '\'' +
                ", availableBooksCount=" + availableBooksCount +
                ", issuedBooksCount=" + issuedBooksCount +
                ", availableBookList=" + availableBookList +
                ", issuedBookList=" + issuedBookList +
                '}';
    }
}
