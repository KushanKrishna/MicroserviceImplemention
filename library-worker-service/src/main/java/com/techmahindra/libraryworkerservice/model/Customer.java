package com.techmahindra.libraryworkerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    Long id;
    String name;
    String age;
    String occupation;
    String email;
    String phoneNo;
    String customerId;
    String password;
    Double totalOutstanding;
    LocalDate userCreationDate;
    LocalTime userCreationTime;
}

