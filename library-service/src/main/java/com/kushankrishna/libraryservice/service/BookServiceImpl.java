package com.kushankrishna.libraryservice.service;

import com.kushankrishna.libraryservice.dto.*;
import com.kushankrishna.libraryservice.repository.BookRepository;
import com.kushankrishna.libraryservice.repository.LibraryRepository;
import com.kushankrishna.libraryservice.model.Book;
import com.kushankrishna.libraryservice.model.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    LibraryRepository libraryRepository;
    @Autowired
    BookServiceUtil bookServiceUtil;

    @Override
    public Book buildAddRequest(AddBookRequestDTO addBookRequestDTO) {
        return Book.builder()
                .bookAuthor(addBookRequestDTO.getBookAuthor())
                .bookPrice(addBookRequestDTO.getBookPrice())
                .bookName(addBookRequestDTO.getBookName())
                .ISBN(addBookRequestDTO.getISBN())
                .publishedDate(addBookRequestDTO.getPublishedDate())
                .publisher(addBookRequestDTO.getPublisher())
                .bookPrice(addBookRequestDTO.getBookPrice())
                .isIssued(false)
                .build();
    }

    @Override
    public Book saveBook(Book book, String library) {

        List<Library> all = this.libraryRepository.findAll();
        Library library2 = all.stream().filter(library1 -> library1.getLibraryName().equals(library.trim())).findAny().get();
        book.setLibrary(library2);

        Book save = this.bookRepository.save(book);
        List<Book> availableBookList = this.bookRepository.findAll().stream().filter(book1 -> book1.getLibrary().equals(library2)).filter(book1 -> !book1.getIssued()).toList();
        library2.setAvailableBooksCount((long) availableBookList.size());
        List<Book> issuedBookList = this.bookRepository.findAll().stream().filter(book1 -> book1.getLibrary().equals(library2)).filter(book1 -> book1.getIssued()).toList();
        library2.setIssuedBooksCount((long) issuedBookList.size());
        this.libraryRepository.save(library2);
        return save;
    }


    @Override
    public List<Book> getBooks(String customerId) {
        List<Book> all = (List<Book>) bookRepository.findAll();
        List<Book> collect = all.stream().filter(book -> book.getCustomerId() != null).filter(book -> book.getCustomerId().equals(customerId)).collect(Collectors.toList());
        return collect;

    }

    @Override
    public Book searchBook(String bookName) {
        List<Book> all = (List<Book>) this.bookRepository.findAll();
        Book book = all.stream().filter(book1 -> book1.getBookName().equals(bookName)).findAny().orElse(null);
        return book;
    }

    @Override
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto, String library) {
        String customerId = issueBookRequestDto.getCustomerId();
        String bookName = issueBookRequestDto.getBookName();
        String author = issueBookRequestDto.getBookAuthor();
        String publisher = issueBookRequestDto.getBookPublisher();
        List<Book> all = this.bookRepository.findAll();
        List<Book> collect = all.stream().filter(book -> !book.getIssued()).toList();
        List<Book> collect1 = collect.stream().filter(book -> book.getBookName().equals(bookName)).filter(book -> book.getBookAuthor().equals(author)).filter(book -> book.getPublisher().equals(publisher)).collect(Collectors.toList());
        Optional<Library> library2 = this.libraryRepository.findAll().stream().filter(lib -> lib.getLibraryName().equals(library)).findFirst();
        if (collect1 != null && library2.isPresent()) {
            System.out.println(collect1);
            if (!collect1.isEmpty()) {
                Library library1 = library2.get();
                System.out.println(collect1);
                Book book = collect1.get(0);
                book.setCustomerId(customerId);
                book.setIssuedDate(LocalDate.now());
                book.setReturnDate(LocalDate.now().plusMonths(3));
                book.setIssued(true);
                this.bookRepository.save(book);
                List<Book> availableBookList = this.bookRepository.findAll().stream().filter(book1 -> book1.getLibrary().equals(library1)).filter(book1 -> !book1.getIssued()).toList();
                library1.setAvailableBooksCount((long) availableBookList.size());
                List<Book> issuedBookList = this.bookRepository.findAll().stream().filter(book1 -> book1.getLibrary().equals(library1)).filter(Book::getIssued).toList();
                library1.setIssuedBooksCount((long) issuedBookList.size());
                this.libraryRepository.save(library1);
                IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
                issueBookResponseDto.setIssuedBookName(book.getBookName());
                issueBookResponseDto.setMessage("Book issued successfully");
                issueBookResponseDto.setIssuedDate(book.getIssuedDate());
                issueBookResponseDto.setReturnDate(book.getReturnDate());
                issueBookResponseDto.setTotalOutStanding(bookServiceUtil.getCustomerOutstanding(customerId));
                System.out.println(issueBookResponseDto);
                return issueBookResponseDto;
            }
        }
        return null;
    }

    @Override
    public ReturnBookResponseDto returnIssuedBook(ReturnBookRequestDto returnBookRequestDto, String library) {
        Optional<Library> library2 = this.libraryRepository.findAll().stream().filter(lib -> lib.getLibraryName().equals(library)).findFirst();
        if (library2.isPresent()) {
            Library library1 = library2.get();
            List<Book> all = this.bookRepository.findAll();
            all.stream().filter(book -> book.getCustomerId().equals(returnBookRequestDto.getCustomerId()));
            boolean b = all.stream().map(book -> book.getBookName()).collect(Collectors.toList()).stream().anyMatch(s -> s.equals(returnBookRequestDto.getBookName()));
            if (b == true) {
                Book book1 = all.stream().filter(book -> book.getCustomerId() != null).filter(book -> book.getCustomerId().equals(returnBookRequestDto.getCustomerId())).findAny().orElse(null);
                if (Objects.nonNull(book1)) {
                    book1.setIssued(false);
                    book1.setReturnDate(null);
                    book1.setIssuedDate(null);
                    book1.setCustomerId(null);
                    this.bookRepository.save(book1);
                    List<Book> availableBookList = this.bookRepository.findAll().stream().filter(book2 -> book2.getLibrary().equals(library1)).filter(book2 -> !book2.getIssued()).toList();
                    library1.setAvailableBooksCount((long) availableBookList.size());
                    List<Book> issuedBookList = this.bookRepository.findAll().stream().filter(book2 -> book2.getLibrary().equals(library1)).filter(Book::getIssued).toList();
                    library1.setIssuedBooksCount((long) issuedBookList.size());
                    this.libraryRepository.save(library1);
                    ReturnBookResponseDto returnBookResponseDto = new ReturnBookResponseDto();
                    returnBookResponseDto.setBookName(book1.getBookName());
                    returnBookResponseDto.setMessage("Book returned successfully");
                    returnBookResponseDto.setBookConditionCheck("Passed");
                    returnBookResponseDto.setLateFee(0d);
                    returnBookResponseDto.setTotalOutstanding(bookServiceUtil.getCustomerOutstanding(returnBookRequestDto.getCustomerId()));
                    return returnBookResponseDto;
                }
            }
            return null;
        }
        return null;
    }
    @Override
    public List<Book> getIssuedBookList() {
        return this.bookRepository.findAll().stream().filter(book -> book.getIssued()).collect(Collectors.toList());
    }

    @Override
    public List<Book> getAvailableBookList() {
        return this.bookRepository.findAll().stream().filter(book -> !book.getIssued()).collect(Collectors.toList());
    }
}
