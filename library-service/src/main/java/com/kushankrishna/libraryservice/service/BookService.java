package com.kushankrishna.libraryservice.service;

import com.kushankrishna.libraryservice.dto.*;
import com.kushankrishna.libraryservice.model.Book;

import java.util.List;

public interface BookService {
    Book buildAddRequest(AddBookRequestDTO addBookRequestDTO);

    Book saveBook(Book book,String library);

    List<Book> getBooks(String customerId);

    Book searchBook(String bookName);

    IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto, String library);

    ReturnBookResponseDto returnIssuedBook(ReturnBookRequestDto returnBookRequestDto, String library);

    List<Book> getIssuedBookList();

    List<Book> getAvailableBookList();
}
