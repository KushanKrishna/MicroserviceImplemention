package com.techmahindra.libraryworkerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDto {

    String name;
    String customerId;
    String age;
    String occupation;
    String email;
    String phoneNo;
    Double totalOutstanding;
    LocalDate userCreationDate;
    LocalTime userCreationTime;

}
