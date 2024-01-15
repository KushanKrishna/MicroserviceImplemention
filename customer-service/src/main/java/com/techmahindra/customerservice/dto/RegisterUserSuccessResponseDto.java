package com.techmahindra.customerservice.dto;

import jdk.jfr.Timespan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserSuccessResponseDto {
    String name;
    String age;
    String occupation;
    String email;
    String phoneNo;
    String customerId;
    String password;
    Double totalOutstanding;

}
