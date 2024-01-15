package com.kushankrishna.libraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRequestDTO {
    String ISBN;
    String bookName;
    String bookAuthor;
    String publisher;
    Double bookPrice;
    LocalDate publishedDate;
}
