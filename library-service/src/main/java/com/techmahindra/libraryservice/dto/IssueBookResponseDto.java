package com.techmahindra.libraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueBookResponseDto {
    String message;
    String issuedBookName;
    LocalDate issuedDate;
    LocalDate returnDate;
    Double totalOutStanding;

}
