package com.techmahindra.libraryservice.service;

import com.techmahindra.libraryservice.model.Book;
import com.techmahindra.libraryservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookServiceUtil {
    @Autowired
    BookRepository bookRepository;

    public Double getCustomerOutstanding(String customerId) {
        List<Book> all = this.bookRepository.findAll();
        List<Book> collect = all.stream().filter(book -> book.getCustomerId() != null).filter(book -> book.getCustomerId().equals(customerId)).collect(Collectors.toList());
        Double totalOutstanding = 0d;
        if (collect != null) {
            if (!collect.isEmpty()) {
                totalOutstanding = collect.stream().map(book -> book.getBookPrice()).collect(Collectors.summingDouble(value -> value));
                return totalOutstanding;
            }
        }
        return totalOutstanding;
    }
}
