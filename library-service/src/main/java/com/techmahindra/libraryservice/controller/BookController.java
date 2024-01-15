package com.techmahindra.libraryservice.controller;

import com.techmahindra.libraryservice.dto.*;
import com.techmahindra.libraryservice.model.Book;
import com.techmahindra.libraryservice.model.CustomerBook;
import com.techmahindra.libraryservice.service.BookService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestParam("Library") String library, @RequestBody AddBookRequestDTO addBookRequestDTO) {
        Book book = this.bookService.buildAddRequest(addBookRequestDTO);
        if (Objects.nonNull(this.bookService.saveBook(book, library))) {
            AddBookResponseDto addBookResponseDto = new AddBookResponseDto();
            addBookResponseDto.setStatus("success");
            addBookResponseDto.setTimestamp(new Timestamp(new Date().getTime()));
            addBookResponseDto.setMessage("Book added Successfully");
            addBookResponseDto.setHttpStatus(HttpStatus.OK.value());
            return new ResponseEntity<>(addBookResponseDto, HttpStatus.OK);
        } else {
            AddBookResponseDto addBookResponseDto = new AddBookResponseDto();
            addBookResponseDto.setStatus("failed");
            addBookResponseDto.setTimestamp(new Timestamp(new Date().getTime()));
            addBookResponseDto.setMessage("Book was not added");
            addBookResponseDto.setHttpStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(addBookResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getIssuedBooks")
    public List<CustomerBook> getIssuedBooks(@PathParam("CustomerId") String customerId) {
        List<Book> books = this.bookService.getBooks(customerId);
//        System.out.println(books);
        List<CustomerBook> list = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            CustomerBook obj = new CustomerBook();
            obj.setBookAuthor(books.get(i).getBookAuthor());
            obj.setBookPrice((books.get(i).getBookPrice()));
            obj.setBookName(books.get(i).getBookName());
            obj.setISBN(books.get(i).getISBN());
            obj.setIssuedDate(books.get(i).getIssuedDate());
            obj.setReturnDate(books.get(i).getReturnDate());
            obj.setPublisher(books.get(i).getPublisher());
            obj.setPublishedDate(books.get(i).getPublishedDate());
            list.add(obj);
        }
        return list;
    }

    @GetMapping("/getBook/{bookName}")
    public Book getBook(@PathVariable("bookName") String bookName) {
        return this.bookService.searchBook(bookName);
    }

    @PostMapping("/issueBooks")
    public ResponseEntity<?> issueBooks(@RequestBody IssueBookRequestDto issueBookRequestDto) {
        if (Objects.nonNull(issueBookRequestDto)) {
            IssueBookResponseDto issueBookResponseDto = this.bookService.issueBook(issueBookRequestDto);
            if (issueBookResponseDto != null) {
                return new ResponseEntity<>(issueBookResponseDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PostMapping("/returnBook")
    public ResponseEntity<?> returnBook(@RequestBody ReturnBookRequestDto returnBookRequestDto) {
        if (Objects.nonNull(returnBookRequestDto)) {
            ReturnBookResponseDto returnBookResponseDto = this.bookService.returnIssuedBook(returnBookRequestDto);
            if (Objects.nonNull(returnBookResponseDto)) {
                return new ResponseEntity<>(returnBookResponseDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/getIssuedBookList")
    public ResponseEntity<?> getIssuedBookList(){
        return new ResponseEntity(this.bookService.getIssuedBookList(),HttpStatus.OK);
    }

    @GetMapping("/getAvailableBookList")
    public ResponseEntity<?> getAvailableBookList(){
        return new ResponseEntity(this.bookService.getAvailableBookList(),HttpStatus.OK);
    }
}
