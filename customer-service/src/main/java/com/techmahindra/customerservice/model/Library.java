package com.techmahindra.customerservice.model;

import java.util.List;

public class Library {
    Long id;
    String libraryName;
    Long availableBooksCount;
    Long issuedBooksCount;
    List<CustomerBook> availableBookList;
    List<CustomerBook> issuedBookList;

}
