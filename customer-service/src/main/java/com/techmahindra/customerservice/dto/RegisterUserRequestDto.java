package com.techmahindra.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequestDto {
    String name;
    String age;
    String occupation;
    String email;
    String phoneNo;

    public static class IssueBookResponseDto {
        String message;
        String issuedBookName;
        LocalDate issuedDate;
        LocalDate returnDate;
        Double totalOutStanding;
    }
}
