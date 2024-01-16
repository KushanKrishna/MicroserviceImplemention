package com.kushankrishna.libraryworkerservice.controller;

import com.kushankrishna.libraryworkerservice.dto.CustomerResponseDto;
import com.kushankrishna.libraryworkerservice.dto.IssueBookReqtDto;
import com.kushankrishna.libraryworkerservice.service.LibraryWorkerService;
import com.kushankrishna.libraryworkerservice.dto.IssuedBooksResponseDto;
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
    /*
    As a library worker you want the list of all the customers of the library, calling this
    API will return the list of all the customers in the library.
    This api internally calls the getAllCustomers API of customer service using rest template.
     */
    @GetMapping("/getAllCustomers")
    public List<CustomerResponseDto> getAllCustomers() {
        return this.libraryWorkerService.getAllCustomersList();
    }
    /*
    As a library worker you want the details of a particular customer.
    Calling this API will return all the details of the customer by passing customer Id in the
    path variable.
     */

    @GetMapping("/getByCustomerId/{customerId}")
    public CustomerResponseDto getCustomerById(@PathVariable("customerId") String customerId) {
        return this.libraryWorkerService.getCustomerById(customerId);
    }

    /*
    As a library worker you can issue a book to any customer.
    Calling this API will get the book issued to a customer if the customer id is valid and the
    book is available.
    This api internally calls the issueBook api of the customer service which then internally
    calls the API of book service.
     */

    @PostMapping("/issueBook")
    public ResponseEntity<?> issueTheBooks(@RequestParam("Library") String library,@RequestBody IssueBookReqtDto issueBookReqtDto) {
        IssuedBooksResponseDto issuedBooksResponseDto = this.libraryWorkerService.issueBook(issueBookReqtDto,library);
        if (Objects.nonNull(issuedBooksResponseDto)) {
            return new ResponseEntity<>(issuedBooksResponseDto, HttpStatus.OK);
        } else return new ResponseEntity<>("Book issue failed", HttpStatus.OK);
    }
}
