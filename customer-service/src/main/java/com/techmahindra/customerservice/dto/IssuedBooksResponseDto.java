package com.techmahindra.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssuedBooksResponseDto {
    String message;
    String issuedBookName;
    LocalDate issuedDate;
    LocalDate returnDate;
    Double totalOutStanding;

}
