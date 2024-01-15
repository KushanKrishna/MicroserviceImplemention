package com.techmahindra.libraryworkerservice.controller;

import com.techmahindra.libraryworkerservice.dto.CustomerResponseDto;
import com.techmahindra.libraryworkerservice.dto.IssueBookReqtDto;
import com.techmahindra.libraryworkerservice.dto.IssuedBooksResponseDto;
import com.techmahindra.libraryworkerservice.service.LibraryWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/library-worker")
public class LibraryWorkerController {

    @Autowired
    LibraryWorkerService libraryWorkerService;

    @GetMapping("/getAllCustomers")
    public List<CustomerResponseDto> getAllCustomers() {
        return this.libraryWorkerService.getAllCustomersList();
    }

    @GetMapping("/getByCustomerId/{customerId}")
    public CustomerResponseDto getCustomerById(@PathVariable("customerId") String customerId) {
        return this.libraryWorkerService.getCustomerById(customerId);
    }

    @PostMapping("/issueBook")
    public ResponseEntity<?> issueTheBooks(@RequestBody IssueBookReqtDto issueBookReqtDto) {
        IssuedBooksResponseDto issuedBooksResponseDto = this.libraryWorkerService.issueBook(issueBookReqtDto);
        if (Objects.nonNull(issuedBooksResponseDto)) {
            return new ResponseEntity<>(issuedBooksResponseDto, HttpStatus.OK);
        } else return new ResponseEntity<>("Book issue failed", HttpStatus.OK);
    }
}
