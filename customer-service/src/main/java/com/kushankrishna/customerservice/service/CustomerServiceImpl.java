package com.kushankrishna.customerservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kushankrishna.customerservice.dto.*;
import com.kushankrishna.customerservice.model.CustomerBook;
import com.kushankrishna.customerservice.model.LibraryCustomer;
import com.kushankrishna.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerServiceUtil customerServiceUtil;

    @Override
    public LibraryCustomer buildCustomer(RegisterUserRequestDto registerUserRequestDto) {
        LibraryCustomer newCustomer = LibraryCustomer.builder().age(registerUserRequestDto.getAge()).email(registerUserRequestDto.getEmail()).name(registerUserRequestDto.getName()).phoneNo(registerUserRequestDto.getPhoneNo()).occupation(registerUserRequestDto.getOccupation()).build();
        return newCustomer;
    }

    @Override
    public LibraryCustomer saveCustomer(LibraryCustomer libraryCustomer) {
        String customerId = customerServiceUtil.getCustomerId(libraryCustomer);
        List<String> collect = this.customerRepository.findAll().stream().map(libraryCustomer1 -> libraryCustomer1.getCustomerId()).collect(Collectors.toList());
        if (!collect.contains(customerId.toString())) {
            libraryCustomer.setCustomerId(customerId);
        } else {
            customerId = customerServiceUtil.getCustomerId(libraryCustomer);
        }

        libraryCustomer.setPassword(customerServiceUtil.getCustomerPwd(libraryCustomer));
        libraryCustomer.setUserCreationTime(LocalTime.now());
        libraryCustomer.setUserCreationDate(LocalDate.now());
        return this.customerRepository.save(libraryCustomer);
    }

    @Override
    public CustomerLoginSuccessResponseDto loginUser(CustomerLoginRequestDto customerLoginRequestDto) {
        List<LibraryCustomer> all = this.customerRepository.findAll();
        LibraryCustomer libraryCustomer = all.stream().filter(libraryCustomer1 -> libraryCustomer1.getCustomerId().equals(customerLoginRequestDto.getCustomerId())).findAny().orElse(null);
        if (libraryCustomer != null) {
            if (libraryCustomer.getPassword().equals(customerLoginRequestDto.getPassword())) {

                if (Objects.nonNull(libraryCustomer)) {
                    String customerId = libraryCustomer.getCustomerId();
                    List<CustomerBook> issuedBookList = this.customerServiceUtil.getIssuedBookList(customerId);
                    Double totalOutStanding = 0.0;
                    if (Objects.nonNull(issuedBookList)) {
                        for (CustomerBook customerBook : issuedBookList) {
                            totalOutStanding += customerBook.getBookPrice();
                        }
                    }
                    CustomerLoginSuccessResponseDto customerLoginSuccessResponseDto = new CustomerLoginSuccessResponseDto();
                    customerLoginSuccessResponseDto.setAge(libraryCustomer.getAge());
                    customerLoginSuccessResponseDto.setCustomerId(libraryCustomer.getCustomerId());
                    customerLoginSuccessResponseDto.setName(libraryCustomer.getName());
                    customerLoginSuccessResponseDto.setOccupation(libraryCustomer.getOccupation());
                    customerLoginSuccessResponseDto.setEmail(libraryCustomer.getEmail());
                    customerLoginSuccessResponseDto.setPassword(libraryCustomer.getPassword());
                    customerLoginSuccessResponseDto.setTotalOutstanding(totalOutStanding);
                    customerLoginSuccessResponseDto.setPhoneNo(libraryCustomer.getPhoneNo());
                    customerLoginSuccessResponseDto.setIssuedBookList(issuedBookList);
                    libraryCustomer.setTotalOutstanding(totalOutStanding);
                    this.customerRepository.save(libraryCustomer);
                    return customerLoginSuccessResponseDto;
                } else return null;
            } else return null;
        } else return null;
    }


    @Override
    public IssuedBooksResponseDto issueBook(IssueBookReqtDto issueBookRequestDto,String library) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        String customerId = issueBookRequestDto.getCustomerId();
        String password = issueBookRequestDto.getPassword();
        String publisher = issueBookRequestDto.getBookPublisher();
        String author = issueBookRequestDto.getBookAuthor();
        String bookName = issueBookRequestDto.getBookName();
        CustomerLoginRequestDto customerLoginRequestDto = new CustomerLoginRequestDto();
        customerLoginRequestDto.setCustomerId(customerId);
        customerLoginRequestDto.setPassword(password);

        List<LibraryCustomer> all = this.customerRepository.findAll();
        LibraryCustomer libraryCustomer = all.stream().filter(libraryCustomer1 -> libraryCustomer1.getCustomerId().equals(customerLoginRequestDto.getCustomerId())).findAny().orElse(null);
        if (libraryCustomer != null) {

            if (libraryCustomer.getPassword().equals(customerLoginRequestDto.getPassword())) {
                IssueBookRequestDto issueBookDto = new IssueBookRequestDto();
                issueBookDto.setBookAuthor(author);
                issueBookDto.setBookPublisher(publisher);
                issueBookDto.setCustomerId(customerId);
                issueBookDto.setBookName(bookName);
                System.out.println(issueBookRequestDto);
                IssuedBooksResponseDto issueBook;
                String baseUrl ="http://LIBRARY-SERVICE/books/issueBooks";
                UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(baseUrl)
                        .queryParam("Library",library);
                String finalUri = uriComponentsBuilder.toUriString();
                issueBook = restTemplate.postForObject(finalUri, issueBookDto, IssuedBooksResponseDto.class);
                System.out.println(issueBook);
                if (Objects.nonNull(issueBook)) {
                    List<CustomerBook> issuedBookList = this.customerServiceUtil.getIssuedBookList(customerId);
                    Double totalOutStanding = 0.0;
                    if (Objects.nonNull(issuedBookList)) {
                        for (CustomerBook customerBook : issuedBookList) {
                            totalOutStanding += customerBook.getBookPrice();
                        }
                    }
                    this.customerRepository.save(libraryCustomer);
                    return issueBook;
                } else {
                    return null;
                }
            } else return null;
        } else return null;
    }

    @Override
    public LibraryCustomer getCustomer(String customerId) {
        List<LibraryCustomer> all = this.customerRepository.findAll();
        LibraryCustomer libraryCustomer1 = all.stream().filter(libraryCustomer -> libraryCustomer.getCustomerId().equals(customerId)).findAny().orElse(null);
        return libraryCustomer1;
    }

    @Override
    public LibraryCustomer save(LibraryCustomer libraryCustomer) {
        return this.customerRepository.save(libraryCustomer);
    }

    @Override
    public ReturnBookResponseDto returnIssuedBook(ReturnBookRequestDto returnBookRequestDto,String library) {
        ObjectMapper mapper = new ObjectMapper();
        List<LibraryCustomer> all = this.customerRepository.findAll();
        LibraryCustomer customer = all.stream().filter(libraryCustomer -> libraryCustomer.getCustomerId().equals(returnBookRequestDto.getCustomerId())).findAny().orElse(null);
        String baseUrl ="http://LIBRARY-SERVICE/books/returnBook";
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("Library",library);
        String finalUri = uriComponentsBuilder.toUriString();
        ReturnBookResponseDto returnBookResponseDto = mapper.convertValue(restTemplate.postForObject(finalUri, returnBookRequestDto, ReturnBookResponseDto.class), ReturnBookResponseDto.class);
        if (Objects.nonNull(returnBookResponseDto)) {
            assert customer != null;
            customer.setTotalOutstanding(returnBookResponseDto.getTotalOutstanding());
            this.customerRepository.save(customer);
            return returnBookResponseDto;
        } else {
            return null;
        }
    }

    @Override
    public ChangePasswordResponseDto changePassword(ChangePasswordRequestDto changePasswordRequestDto) {
        if ((Objects.nonNull(changePasswordRequestDto))) {
            String customerId = changePasswordRequestDto.getCustomerId();
            if (this.customerRepository.findAll().stream().map(libraryCustomer -> libraryCustomer.getCustomerId()).collect(Collectors.toList()).contains(customerId)) {
                LibraryCustomer customer = this.customerRepository.findAll().stream().filter(libraryCustomer -> libraryCustomer.getCustomerId().equals(customerId)).findAny().orElse(null);
                if (customer.getPassword().equals(changePasswordRequestDto.getCurrentPassword())) {
                    customer.setPassword(changePasswordRequestDto.getNewPassword());
                    this.customerRepository.save(customer);
                    ChangePasswordResponseDto changePasswordResponseDto = new ChangePasswordResponseDto();
                    changePasswordResponseDto.setMessage("Password changed successfully!");
                    return changePasswordResponseDto;
                } else {
                    ChangePasswordResponseDto changePasswordResponseDto = new ChangePasswordResponseDto();
                    changePasswordResponseDto.setMessage("Incorrect old password, password was not changed!");
                    return changePasswordResponseDto;
                }

            } else {
                ChangePasswordResponseDto changePasswordResponseDto = new ChangePasswordResponseDto();
                changePasswordResponseDto.setMessage("Invalid customer id, password was not changed!");
                return changePasswordResponseDto;
            }
        } else {
            ChangePasswordResponseDto changePasswordResponseDto = new ChangePasswordResponseDto();
            changePasswordResponseDto.setMessage("Invalid request, password was not changed!");
            return changePasswordResponseDto;
        }
    }

    @Override
    public List<LibraryCustomer> getAllCustomers() {
        return this.customerRepository.findAll();
    }


}