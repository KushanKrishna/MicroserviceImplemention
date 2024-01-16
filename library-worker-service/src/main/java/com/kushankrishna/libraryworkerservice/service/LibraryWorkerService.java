package com.kushankrishna.libraryworkerservice.service;

import com.kushankrishna.libraryworkerservice.dto.CustomerResponseDto;
import com.kushankrishna.libraryworkerservice.dto.IssueBookReqtDto;
import com.kushankrishna.libraryworkerservice.dto.IssuedBooksResponseDto;

import java.util.List;

public interface LibraryWorkerService {
    List<CustomerResponseDto> getAllCustomersList();

    CustomerResponseDto getCustomerById(String customerId);

    IssuedBooksResponseDto issueBook(IssueBookReqtDto issueBookReqtDto, String library);
}
