package com.kushankrishna.customerservice.dto;

import com.kushankrishna.customerservice.model.CustomerBook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLoginSuccessResponseDto {
    String name;
    String age;
    String occupation;
    String email;
    String phoneNo;
    String customerId;
    String password;
    Double totalOutstanding;
    List<CustomerBook> issuedBookList;
}