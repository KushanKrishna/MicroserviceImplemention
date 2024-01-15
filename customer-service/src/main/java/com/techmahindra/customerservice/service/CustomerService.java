package com.techmahindra.customerservice.service;

import com.techmahindra.customerservice.dto.*;
import com.techmahindra.customerservice.model.CustomerBook;
import com.techmahindra.customerservice.model.LibraryCustomer;

import java.util.List;

public interface CustomerService {

    LibraryCustomer buildCustomer(RegisterUserRequestDto registerUserRequestDto);
    LibraryCustomer saveCustomer(LibraryCustomer libraryCustomer);

    CustomerLoginSuccessResponseDto loginUser(CustomerLoginRequestDto customerLoginRequestDto);

    IssuedBooksResponseDto issueBook(IssueBookReqtDto issueBookRequestDto);

    LibraryCustomer getCustomer(String customerId);

    LibraryCustomer save(LibraryCustomer libraryCustomer);

    ReturnBookResponseDto returnIssuedBook(ReturnBookRequestDto returnBookRequestDto);

    ChangePasswordResponseDto changePassword(ChangePasswordRequestDto changePasswordRequestDto);

    List<LibraryCustomer> getAllCustomers();
}
