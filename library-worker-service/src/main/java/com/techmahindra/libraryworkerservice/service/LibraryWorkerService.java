package com.techmahindra.libraryworkerservice.service;

import com.techmahindra.libraryworkerservice.dto.CustomerResponseDto;
import com.techmahindra.libraryworkerservice.dto.IssueBookReqtDto;
import com.techmahindra.libraryworkerservice.dto.IssuedBooksResponseDto;

import java.util.List;

public interface LibraryWorkerService {
    List<CustomerResponseDto> getAllCustomersList();

    CustomerResponseDto getCustomerById(String customerId);

    IssuedBooksResponseDto issueBook(IssueBookReqtDto issueBookReqtDto);
}
