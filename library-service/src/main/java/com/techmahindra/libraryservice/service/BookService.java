package com.techmahindra.libraryservice.service;

import com.techmahindra.libraryservice.dto.*;
import com.techmahindra.libraryservice.model.Book;

import java.util.List;

public interface BookService {
    Book buildAddRequest(AddBookRequestDTO addBookRequestDTO);

    Book saveBook(Book book,String library);

    List<Book> getBooks(String customerId);

    Book searchBook(String bookName);

    IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto);

    ReturnBookResponseDto returnIssuedBook(ReturnBookRequestDto returnBookRequestDto);

    List<Book> getIssuedBookList();

    List<Book> getAvailableBookList();
}
