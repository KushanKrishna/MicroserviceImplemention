package com.kushankrishna.libraryworkerservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kushankrishna.libraryworkerservice.dto.CustomerResponseDto;
import com.kushankrishna.libraryworkerservice.dto.IssueBookReqtDto;
import com.kushankrishna.libraryworkerservice.dto.IssuedBooksResponseDto;
import com.kushankrishna.libraryworkerservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryWorkerServiceImpl implements LibraryWorkerService {

    @Autowired
    LibraryWorkerServiceUtil libraryWorkerServiceUtil;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<CustomerResponseDto> getAllCustomersList() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        List<Customer> customers = mapper.convertValue(restTemplate.getForObject("http://customer-service/customer/getAllCustomers", List.class), new TypeReference<List<Customer>>() {
        });
        List<CustomerResponseDto> customerResponseDtos = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerResponseDto customerResponseDto = libraryWorkerServiceUtil.buildCustomerListResponse(customer);
            customerResponseDtos.add(customerResponseDto);
        }
        return customerResponseDtos;
    }

    @Override
    public CustomerResponseDto getCustomerById(String customerId) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        try {
            List<Customer> customers = mapper.convertValue(restTemplate.getForObject("http://customer-service/customer/getAllCustomers", List.class), new TypeReference<List<Customer>>() {
            });
            Customer customer = customers.stream().filter(cust -> cust.getCustomerId().equals(customerId)).findAny().orElse(null);
            if (customer != null) {
                CustomerResponseDto customerResponseDto = this.libraryWorkerServiceUtil.buildCustomerListResponse(customer);
                return customerResponseDto;
            } else {
                return null;
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public IssuedBooksResponseDto issueBook(IssueBookReqtDto issueBookReqtDto ,String library) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        IssuedBooksResponseDto issuedBooksResponseDto = new IssuedBooksResponseDto();
        try {
            issuedBooksResponseDto = mapper.convertValue(restTemplate.postForObject("http://customer-service/customer/issueBooks?Library="+library, issueBookReqtDto, IssuedBooksResponseDto.class), IssuedBooksResponseDto.class);
            return issuedBooksResponseDto;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
