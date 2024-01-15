package com.techmahindra.libraryservice.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBook {
    String ISBN;
    String bookName;
    String bookAuthor;
    String publisher;
    Double bookPrice;
    LocalDate publishedDate;
    LocalDate issuedDate;
    LocalDate returnDate;
}
