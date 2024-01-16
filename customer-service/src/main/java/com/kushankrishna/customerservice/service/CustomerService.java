package com.kushankrishna.customerservice.service;

import com.kushankrishna.customerservice.dto.*;
import com.kushankrishna.customerservice.model.LibraryCustomer;

import java.util.List;

public interface CustomerService {

    LibraryCustomer buildCustomer(RegisterUserRequestDto registerUserRequestDto);
    LibraryCustomer saveCustomer(LibraryCustomer libraryCustomer);

    CustomerLoginSuccessResponseDto loginUser(CustomerLoginRequestDto customerLoginRequestDto);

    IssuedBooksResponseDto issueBook(IssueBookReqtDto issueBookRequestDto,String library);

    LibraryCustomer getCustomer(String customerId);

    LibraryCustomer save(LibraryCustomer libraryCustomer);

    ReturnBookResponseDto returnIssuedBook(ReturnBookRequestDto returnBookRequestDto,String library);

    ChangePasswordResponseDto changePassword(ChangePasswordRequestDto changePasswordRequestDto);

    List<LibraryCustomer> getAllCustomers();
}
