package com.kushankrishna.customerservice.controller;

import com.kushankrishna.customerservice.dto.*;
import com.kushankrishna.customerservice.model.LibraryCustomer;
import com.kushankrishna.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/addCustomer")
    public ResponseEntity<?> addUser(@RequestBody RegisterUserRequestDto registerUserRequestDto) {
        LibraryCustomer libraryCustomer = this.customerService.buildCustomer(registerUserRequestDto);
        LibraryCustomer customer = this.customerService.saveCustomer(libraryCustomer);
        if (Objects.nonNull(customer)) {
            RegisterUserSuccessResponseDto registerUserSuccessResponseDto = new RegisterUserSuccessResponseDto();
            registerUserSuccessResponseDto.setCustomerId(customer.getCustomerId());
            registerUserSuccessResponseDto.setAge(customer.getAge());
            registerUserSuccessResponseDto.setName(customer.getName());
            registerUserSuccessResponseDto.setEmail(customer.getEmail());
            registerUserSuccessResponseDto.setOccupation(customer.getOccupation());
            registerUserSuccessResponseDto.setPassword(customer.getPassword());
            registerUserSuccessResponseDto.setPhoneNo(customer.getPhoneNo());
            registerUserSuccessResponseDto.setTotalOutstanding(customer.getTotalOutstanding());
            return new ResponseEntity<>(registerUserSuccessResponseDto, HttpStatus.OK);
        } else {
            RegisterUserFailedResponseDto registerUserFailedResponseDto = new RegisterUserFailedResponseDto();
            registerUserFailedResponseDto.setStatus("Failed");
            registerUserFailedResponseDto.setMessage("Customer data not added");
            registerUserFailedResponseDto.setTimestamp(new Timestamp(new Date().getTime()));
            registerUserFailedResponseDto.setHttpStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(registerUserFailedResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CustomerLoginRequestDto customerLoginRequestDto) {
        CustomerLoginSuccessResponseDto customerLoginSuccessResponseDto = this.customerService.loginUser(customerLoginRequestDto);
        if (Objects.nonNull(customerLoginSuccessResponseDto)) {
            return new ResponseEntity<>(customerLoginSuccessResponseDto, HttpStatus.OK);
        } else {
            CustomerLoginFailedResponse dto = new CustomerLoginFailedResponse();
            dto.setHttpStatus(HttpStatus.BAD_REQUEST.value());
            dto.setStatus("failed");
            dto.setMessage("Login unsuccessful");
            dto.setTimestamp(new Timestamp(new Date().getTime()));
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/issueBooks")
    public ResponseEntity<?> issueBooks(@RequestParam("Library") String library,@RequestBody IssueBookReqtDto issueBookRequestDto) {
        IssuedBooksResponseDto issuedBooksResponseDto = this.customerService.issueBook(issueBookRequestDto,library);

        if (Objects.nonNull(issuedBooksResponseDto)) {
            LibraryCustomer libraryCustomer = this.customerService.getCustomer(issueBookRequestDto.getCustomerId());
            libraryCustomer.setTotalOutstanding(issuedBooksResponseDto.getTotalOutStanding());
            this.customerService.save(libraryCustomer);
            return new ResponseEntity<>(issuedBooksResponseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book issue failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/returnBook")
    public ResponseEntity<?> returnBook(@RequestParam("Library") String library,@RequestBody ReturnBookRequestDto returnBookRequestDto) {
        if (Objects.nonNull(returnBookRequestDto)) {
            ReturnBookResponseDto returnBookResponseDto = this.customerService.returnIssuedBook(returnBookRequestDto,library);
            if (Objects.nonNull(returnBookResponseDto)) {
                return new ResponseEntity<>(returnBookResponseDto, HttpStatus.OK);
            }
            return new ResponseEntity<>("Book return failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Book return failed", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/setCustomPassword")
    public ResponseEntity<?> setCustomPwd(@RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
        ChangePasswordResponseDto changePasswordResponseDto = this.customerService.changePassword(changePasswordRequestDto);
        return new ResponseEntity<>(changePasswordResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getAllCustomers")
    public List<LibraryCustomer> getAllCustomers() {
        return this.customerService.getAllCustomers();
    }

}
